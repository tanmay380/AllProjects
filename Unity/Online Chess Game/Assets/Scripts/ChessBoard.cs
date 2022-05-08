using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using Unity.Networking.Transport;
using UnityEngine;
using UnityEngine.UI;

public enum SpeciaMove
{
    None = 0,
    Enpassant,
    Castling,
    Promition
}


public class ChessBoard : MonoBehaviour
{
    [Header("Art stuff")]
    [SerializeField] private Material tilematerial;
    [SerializeField] private float tileSize = 1.0f;
    [SerializeField] private float yOffset = 0.2f;
    [SerializeField] private Vector3 boardCenter = Vector3.zero;
    [SerializeField] private float deathSize = 0.3f;
    [SerializeField] private float deathSpacing = 0.3f;
    [SerializeField] private float dragOffset = 1f;
    [SerializeField] private GameObject victoryScreen;
    [SerializeField] private Transform reamtchIndicator;
    [SerializeField] private Button rematchButton;

    [Header("Prefabs & Material")]
    [SerializeField] private GameObject[] prefabs;
    [SerializeField] private Material[] teamMaterials;

    [SerializeField] private TMP_InputField meesage;


    // LOGIC 
    private ChessPiece[,] chessPieces;
    private ChessPiece currentlyDragging;
    private List<Vector2Int> availableMoves = new List<Vector2Int>();
    private List<ChessPiece> deadWhites = new List<ChessPiece>();
    private List<ChessPiece> deadBlacks = new List<ChessPiece>();
    private const int TILE_COUNT_X = 8;
    private const int TILE_COUNT_Y = 8;
    private GameObject[,] tiles;
    private Camera currentCamera;
    private Vector2Int currentHover;
    private Vector3 bounds;
    private bool isWhiteTurn;

    private List<Vector2Int[]> moveList = new List<Vector2Int[]>();
    private SpeciaMove speciaMove;
    //Mutiplyer logic
    private int playerCount = -1;
    private int currentTeam = -1;
    private bool localGame = true;
    private bool[] playerRematch = new bool[2];



    private void Start()
    {
        GenerateAllTiles(tileSize, TILE_COUNT_X, TILE_COUNT_Y);
        SpawnAllPieces();
        PositionAllPieces();

        isWhiteTurn = true;

        RegisterEvents();
    }



    private void Update()
    {
        if (!currentCamera)
        {
            currentCamera = Camera.main;
            return;
        }

        RaycastHit info;
        Ray ray = currentCamera.ScreenPointToRay(Input.mousePosition);
        if (Physics.Raycast(ray, out info, 100, LayerMask.GetMask("Tile", "Hover", "Highlight")))
        {
            Vector2Int hitPosition = LookupTileIndex(info.transform.gameObject);

            //If we're hovering a tile after not hovering any tiles
            if (currentHover == -Vector2Int.one)
            {
                currentHover = hitPosition;
                tiles[hitPosition.x, hitPosition.y].layer = LayerMask.NameToLayer("Hover");
            }

            // If we were laready hovering a tile, change the previous one
            if (currentHover != hitPosition)
            {
                tiles[currentHover.x, currentHover.y].layer = (ContainsValidMove(ref availableMoves, currentHover)) ? LayerMask.NameToLayer("Highlight") : LayerMask.NameToLayer("Tile");
                currentHover = hitPosition;
                tiles[hitPosition.x, hitPosition.y].layer = LayerMask.NameToLayer("Hover");
            }

            //If we press down in the mouse
            if (Input.GetMouseButtonDown(0))
            {
                if (chessPieces[hitPosition.x, hitPosition.y] != null)
                {
                    // Is it our turn 
                    if ((chessPieces[hitPosition.x, hitPosition.y].team == 0 && isWhiteTurn && currentTeam == 0) || (chessPieces[hitPosition.x, hitPosition.y].team == 1 && !isWhiteTurn && currentTeam == 1))
                    {
                        currentlyDragging = chessPieces[hitPosition.x, hitPosition.y];

                        availableMoves = currentlyDragging.GetAvailableMoves(ref chessPieces, TILE_COUNT_X, TILE_COUNT_Y);
                        //get a list of special moves
                        speciaMove = currentlyDragging.GetSpecialMoves(ref chessPieces, ref moveList, ref availableMoves);

                        PreventCheck();
                        HighlightTiles();
                    }
                }
            }

            //If we are realsing the mouse button
            if (currentlyDragging != null && Input.GetMouseButtonUp(0))
            {
                Vector2Int previousPostion = new Vector2Int(currentlyDragging.CurrentX, currentlyDragging.CurrentY);
                if (ContainsValidMove(ref availableMoves, new Vector2Int(hitPosition.x, hitPosition.y)))
                {
                    MoveTo(previousPostion.x, previousPostion.y, hitPosition.x, hitPosition.y);

                    NetMakeMove nm = new NetMakeMove();
                    nm.originalX = previousPostion.x;
                    nm.originalY = previousPostion.y;
                    nm.destinationX = hitPosition.x;
                    nm.destinationY = hitPosition.y;
                    nm.teamId = currentTeam;
                    client.Instance.SendToServer(nm);

                }
                else
                {
                    currentlyDragging.SetPosition(GetTileCenter(previousPostion.x, previousPostion.y));
                    currentlyDragging = null;
                    removeHighlightTiles();
                }
            }
        }
        else
        {
            if (currentHover != -Vector2Int.one)
            {
                tiles[currentHover.x, currentHover.y].layer = (ContainsValidMove(ref availableMoves, currentHover)) ? LayerMask.NameToLayer("Highlight") : LayerMask.NameToLayer("Tile");
                currentHover = -Vector2Int.one;
            }

            if (currentlyDragging && Input.GetMouseButtonUp(0))
            {
                currentlyDragging.SetPosition(GetTileCenter(currentlyDragging.CurrentX, currentlyDragging.CurrentY));
                currentlyDragging = null;
                removeHighlightTiles();
            }
        }

        // If we're dragging a piece
        if (currentlyDragging)
        {
            Plane horizontalPlane = new Plane(Vector3.up, Vector3.up * yOffset);
            float distance = 0.0f;
            if (horizontalPlane.Raycast(ray, out distance))
                currentlyDragging.SetPosition(ray.GetPoint(distance) + Vector3.up * dragOffset);

        }

    }
    //Generate the board
    private void GenerateAllTiles(float tileSize, int tileCountX, int tileCountY)
    {
        yOffset += transform.position.y;
        bounds = new Vector3((tileCountX / 2) * tileSize, 0, (tileCountX / 2) * tileSize) + boardCenter;

        tiles = new GameObject[tileCountX, tileCountY];
        for (int x = 0; x < tileCountX; x++)

            for (int y = 0; y < tileCountY; y++)
                tiles[x, y] = GenerateSingleTile(tileSize, x, y);

    }
    private GameObject GenerateSingleTile(float tileSize, int x, int y)
    {
        GameObject tileObject = new GameObject(string.Format($"X:{x}, Y:{y}"));
        tileObject.transform.parent = transform;

        Mesh mesh = new Mesh();
        tileObject.AddComponent<MeshFilter>().mesh = mesh;
        tileObject.AddComponent<MeshRenderer>().material = tilematerial;

        Vector3[] vertices = new Vector3[4];
        vertices[0] = new Vector3(x * tileSize, yOffset, y * tileSize) - bounds;
        vertices[1] = new Vector3(x * tileSize, yOffset, (y + 1) * tileSize) - bounds;
        vertices[2] = new Vector3((x + 1) * tileSize, yOffset, (y) * tileSize) - bounds;
        vertices[3] = new Vector3((x + 1) * tileSize, yOffset, (y + 1) * tileSize) - bounds;

        int[] tris = new int[] { 0, 1, 2, 1, 3, 2 };

        mesh.vertices = vertices;
        mesh.triangles = tris;
        mesh.RecalculateNormals();

        tileObject.layer = LayerMask.NameToLayer("Tile");
        tileObject.AddComponent<BoxCollider>();

        return tileObject;
    }
    //Spawing of the pieces
    private void SpawnAllPieces()
    {
        chessPieces = new ChessPiece[TILE_COUNT_X, TILE_COUNT_Y];

        int white = 0;
        int black = 1;
        //WhiteTeam
        chessPieces[0, 0] = SpawnSinglePiece(ChessPieceType.Rook, white);
        chessPieces[1, 0] = SpawnSinglePiece(ChessPieceType.Knight, white);
        chessPieces[2, 0] = SpawnSinglePiece(ChessPieceType.Bishop, white);
        chessPieces[3, 0] = SpawnSinglePiece(ChessPieceType.Queen, white);
        chessPieces[4, 0] = SpawnSinglePiece(ChessPieceType.King, white);
        chessPieces[5, 0] = SpawnSinglePiece(ChessPieceType.Bishop, white);
        chessPieces[6, 0] = SpawnSinglePiece(ChessPieceType.Knight, white);
        chessPieces[7, 0] = SpawnSinglePiece(ChessPieceType.Rook, white);

        //WHitePawn
        for (int x = 0; x < TILE_COUNT_X; x++)
        {

            chessPieces[x, 1] = SpawnSinglePiece(ChessPieceType.Pawn, white);
        }
        //BlackTeam
        chessPieces[0, 7] = SpawnSinglePiece(ChessPieceType.Rook, black);
        chessPieces[1, 7] = SpawnSinglePiece(ChessPieceType.Knight, black);
        chessPieces[2, 7] = SpawnSinglePiece(ChessPieceType.Bishop, black);
        chessPieces[3, 7] = SpawnSinglePiece(ChessPieceType.Queen, black);
        chessPieces[4, 7] = SpawnSinglePiece(ChessPieceType.King, black);
        chessPieces[5, 7] = SpawnSinglePiece(ChessPieceType.Bishop, black);
        chessPieces[6, 7] = SpawnSinglePiece(ChessPieceType.Knight, black);
        chessPieces[7, 7] = SpawnSinglePiece(ChessPieceType.Rook, black);

        //WHitePawn
        for (int x = 0; x < TILE_COUNT_X; x++)
        {

            chessPieces[x, 6] = SpawnSinglePiece(ChessPieceType.Pawn, black);
        }
    }
    private ChessPiece SpawnSinglePiece(ChessPieceType type, int team)
    {
        ChessPiece cp = Instantiate(prefabs[(int)type - 1], transform).GetComponent<ChessPiece>();

        cp.type = type;
        cp.team = team;
        cp.GetComponent<MeshRenderer>().material = teamMaterials[team];

        return cp;
    }
    //Positioning
    private void PositionAllPieces()
    {
        for (int x = 0; x < TILE_COUNT_X; x++)
            for (int y = 0; y < TILE_COUNT_Y; y++)
                if (chessPieces[x, y] != null)
                    PositionSinglePieces(x, y, true);
    }
    private void PositionSinglePieces(int x, int y, bool force = false)
    {
        chessPieces[x, y].CurrentX = x;
        chessPieces[x, y].CurrentY = y;
        chessPieces[x, y].SetPosition(GetTileCenter(x, y), force);
    }
    private Vector3 GetTileCenter(int x, int y)
    {
        return new Vector3(x * tileSize, yOffset, y * tileSize) - bounds + new Vector3(tileSize / 2, 0, tileSize / 2);
    }
    //Highlight
    private void HighlightTiles()
    {
        for (int i = 0; i < availableMoves.Count; i++)
        {
            tiles[availableMoves[i].x, availableMoves[i].y].layer = LayerMask.NameToLayer("Highlight");
        }
    }
    private void removeHighlightTiles()
    {
        for (int i = 0; i < availableMoves.Count; i++)
        {
            tiles[availableMoves[i].x, availableMoves[i].y].layer = LayerMask.NameToLayer("Tile");
        }
        availableMoves.Clear();
    }

    //CheckMate
    private void CheckMate(int team)
    {
        DisplayVictory(team);
    }
    private void DisplayVictory(int winningteam)
    {
        victoryScreen.SetActive(true);
        victoryScreen.transform.GetChild(winningteam).gameObject.SetActive(true);
    }
    public void onSendMessage()
    {
        SendMessage send = new SendMessage();
        send.ChatMessage = 1.01f;
        client.Instance.SendToServer(send);
    }


    public void onRematchButton()
    {
        if (localGame)
        {
            NetReamtch wrm = new NetReamtch();
            wrm.teamId = 0;
            wrm.wantRematch = 1;
            client.Instance.SendToServer(wrm);


            NetReamtch brm = new NetReamtch();
            brm.teamId = 1;
            brm.wantRematch = 1;
            client.Instance.SendToServer(brm);
        }
        else
        {
            NetReamtch rm = new NetReamtch();
            rm.teamId = currentTeam;
            rm.wantRematch = 1;
            client.Instance.SendToServer(rm);
        }


    }

    public void GameReset()
    {

        //UI

        rematchButton.interactable = true;
            reamtchIndicator.transform.GetChild(0).gameObject.SetActive(false);
        reamtchIndicator.transform.GetChild(1).gameObject.SetActive(false);

        victoryScreen.transform.GetChild(0).gameObject.SetActive(false);
        victoryScreen.transform.GetChild(1).gameObject.SetActive(false);
        victoryScreen.SetActive(false);

        //Field rest
        currentlyDragging = null;
        availableMoves.Clear();
        moveList.Clear();
        playerRematch[0] = playerRematch[1] = false;

        //Clean Up
        for (int i = 0; i < TILE_COUNT_X; i++)
        {
            for (int j = 0; j < TILE_COUNT_Y; j++)
            {
                if (chessPieces[i, j] != null)
                    Destroy(chessPieces[i, j].gameObject);

                chessPieces[i, j] = null;
            }
        }
        for (int i = 0; i < deadWhites.Count; i++)
            Destroy(deadWhites[i].gameObject);

        for (int i = 0; i < deadBlacks.Count; i++)
            Destroy(deadBlacks[i].gameObject);

        deadWhites.Clear();
        deadBlacks.Clear();

        SpawnAllPieces();
        PositionAllPieces();

        isWhiteTurn = true;

    }
    public void OnMenuButton()
    {

        NetReamtch rm = new NetReamtch();
        rm.teamId = currentTeam;
        rm.wantRematch = 0;
        client.Instance.SendToServer(rm);

        GameReset();
        gameui.Instance.OnLeaveFromGameMenu();

        Invoke("ShutdownServer", 1.0f);

        playerCount = -1;
        currentTeam = -1;

    }
    private void ProcessSpecialMove()
    {
        if (speciaMove == SpeciaMove.Enpassant)
        {
            var newMove = moveList[moveList.Count - 1];
            ChessPiece myPawn = chessPieces[newMove[1].x, newMove[1].y];
            var targetPawnPosition = moveList[moveList.Count - 2];
            ChessPiece enemyPawn = chessPieces[targetPawnPosition[1].x, targetPawnPosition[1].y];

            if (myPawn.CurrentX == enemyPawn.CurrentX)
                if (myPawn.CurrentY == enemyPawn.CurrentY - 1 || myPawn.CurrentY == enemyPawn.CurrentY + 1)
                {
                    if (enemyPawn.team == 0)
                    {
                        deadWhites.Add(enemyPawn);
                        enemyPawn.SetScale(Vector3.one * deathSize);
                        enemyPawn.SetPosition(new Vector3(-1 * tileSize, yOffset, 8 * tileSize)
                            - bounds + new Vector3(tileSize / 2, 0, tileSize / 2) + (Vector3.back * deathSpacing) * deadBlacks.Count);
                    }
                    else
                    {
                        deadBlacks.Add(enemyPawn);
                        enemyPawn.SetScale(Vector3.one * deathSize);
                        enemyPawn.SetPosition(new Vector3(-1 * tileSize, yOffset, 8 * tileSize)
                            - bounds + new Vector3(tileSize / 2, 0, tileSize / 2) + (Vector3.back * deathSpacing) * deadBlacks.Count);

                    }
                    chessPieces[enemyPawn.CurrentX, enemyPawn.CurrentY] = null;
                }
        }

        if (speciaMove == SpeciaMove.Promition)
        {
            Vector2Int[] lastmoce = moveList[moveList.Count - 1];
            ChessPiece cp = chessPieces[lastmoce[1].x, lastmoce[1].y];

            if (cp.type == ChessPieceType.Pawn)
            {
                if (cp.team == 0 && lastmoce[1].y == 7)
                {
                    ChessPiece Queen = SpawnSinglePiece(ChessPieceType.Queen, 0);
                    Queen.transform.position = chessPieces[lastmoce[1].x, lastmoce[1].y].transform.position;
                    Destroy(chessPieces[lastmoce[1].x, lastmoce[1].y].gameObject);
                    chessPieces[lastmoce[1].x, lastmoce[1].y] = Queen;
                    PositionSinglePieces(lastmoce[1].x, lastmoce[1].y);
                }
                if (cp.team == 1 && lastmoce[1].y == 0)
                {
                    ChessPiece Queen = SpawnSinglePiece(ChessPieceType.Queen, 0);
                    Queen.transform.position = chessPieces[lastmoce[1].x, lastmoce[1].y].transform.position;
                    Destroy(chessPieces[lastmoce[1].x, lastmoce[1].y].gameObject);
                    chessPieces[lastmoce[1].x, lastmoce[1].y] = Queen;
                    PositionSinglePieces(lastmoce[1].x, lastmoce[1].y);
                }
            }

        }

        if (speciaMove == SpeciaMove.Castling)
        {
            var lastMove = moveList[moveList.Count - 1];

            //LEft Rook
            if (lastMove[1].x == 2)
            {
                if (lastMove[1].y == 0)
                {
                    ChessPiece rook = chessPieces[0, 0];
                    chessPieces[3, 0] = rook;
                    PositionSinglePieces(3, 0);
                    chessPieces[0, 0] = null;
                }
                else if (lastMove[1].y == 7)
                {
                    ChessPiece rook = chessPieces[0, 7];
                    chessPieces[3, 7] = rook;
                    PositionSinglePieces(3, 7);
                    chessPieces[0, 7] = null;
                }
            }
            //Right ROok
            else if (lastMove[1].x == 6)
            {
                if (lastMove[1].y == 0)
                {
                    ChessPiece rook = chessPieces[7, 0];
                    chessPieces[5, 0] = rook;
                    PositionSinglePieces(5, 0);
                    chessPieces[7, 0] = null;
                }
                else if (lastMove[1].y == 7)
                {
                    ChessPiece rook = chessPieces[7, 7];
                    chessPieces[5, 7] = rook;
                    PositionSinglePieces(5, 7);
                    chessPieces[7, 7] = null;
                }
            }


        }
    }

    private void PreventCheck()
    {
        ChessPiece targetKing = null;
        for (int i = 0; i < TILE_COUNT_X; i++)
        {
            for (int j = 0; j < TILE_COUNT_Y; j++)
            {
                if (chessPieces[i, j] != null)
                {
                    if (chessPieces[i, j].type == ChessPieceType.King)
                    {
                        if (chessPieces[i, j].team == currentlyDragging.team)
                        {
                            targetKing = chessPieces[i, j];
                        }
                    }
                }
            }
        }

        SimulateMoveForSInglePIece(currentlyDragging, ref availableMoves, targetKing);
    }

    private void SimulateMoveForSInglePIece(ChessPiece cp, ref List<Vector2Int> moves, ChessPiece targetKing)
    {
        //SAve the current values, to reset after the function call
        int actualX = cp.CurrentX;
        int actualY = cp.CurrentY;
        List<Vector2Int> movesToRemove = new List<Vector2Int>();

        //Going through all the moves, simulate them and check if we're in check
        for (int i = 0; i < moves.Count; i++)
        {
            int simX = moves[i].x;
            int simY = moves[i].y;

            Vector2Int kingPositionThisSIim = new Vector2Int(targetKing.CurrentX, targetKing.CurrentY);

            if (cp.type == ChessPieceType.King)
            {
                kingPositionThisSIim = new Vector2Int(simX, simY);

            }
            ChessPiece[,] simulation = new ChessPiece[TILE_COUNT_X, TILE_COUNT_Y];
            List<ChessPiece> simAttackingPence = new List<ChessPiece>();
            for (int x = 0; x < TILE_COUNT_X; x++)
            {
                for (int k = 0; k < TILE_COUNT_Y; k++)
                {
                    if (chessPieces[x, k] != null)
                    {
                        simulation[x, k] = chessPieces[x, k];
                        if (simulation[x, k].team != cp.team)
                        {
                            simAttackingPence.Add(simulation[x, k]);
                        }
                    }
                }
            }
            simulation[actualX, actualY] = null;
            cp.CurrentX = simX;
            cp.CurrentY = simY;
            simulation[simX, simY] = cp;

            var deadPience = simAttackingPence.Find(c => c.CurrentX == simX && c.CurrentY == simY);
            if (deadPience != null)
                simAttackingPence.Remove(deadPience);

            // EGt all the simulated attacking pieces move 
            List<Vector2Int> simMoves = new List<Vector2Int>();
            for (int a = 0; a < simAttackingPence.Count; a++)
            {
                var pieceMoves = simAttackingPence[a].GetAvailableMoves(ref simulation, TILE_COUNT_X, TILE_COUNT_Y);
                for (int b = 0; b < pieceMoves.Count; b++)
                {
                    simMoves.Add(pieceMoves[b]);
                }
            }
            if (ContainsValidMove(ref simMoves, kingPositionThisSIim))
            {
                movesToRemove.Add(moves[i]);
            }
            cp.CurrentX = actualX;
            cp.CurrentY = actualY;
        }

        //Remove form the current available move list
        for (int i = 0; i < movesToRemove.Count; i++)
        {
            moves.Remove(movesToRemove[i]);
        }
    }

    private bool CheckForCheckMate()
    {
        var lastMove = moveList[moveList.Count - 1];
        int targetTeam = (chessPieces[lastMove[1].x, lastMove[1].y].team == 0) ? 1 : 0;

        List<ChessPiece> atackingpiece = new List<ChessPiece>();
        List<ChessPiece> defendingPieces = new List<ChessPiece>();
        ChessPiece targetKing = null;
        for (int i = 0; i < TILE_COUNT_X; i++)
        {
            for (int j = 0; j < TILE_COUNT_Y; j++)
            {
                if (chessPieces[i, j] != null)
                {
                    if (chessPieces[i, j].team == targetTeam)
                    {
                        defendingPieces.Add(chessPieces[i, j]);
                        if (chessPieces[i, j].type == ChessPieceType.King)
                            targetKing = chessPieces[i, j];
                    }
                    else
                    {
                        atackingpiece.Add(chessPieces[i, j]);
                    }
                }
            }

        }
        //Is the king attacked right now
        List<Vector2Int> currentAvailableMoves = new List<Vector2Int>();
        for (int i = 0; i < atackingpiece.Count; i++)
        {
            var pieceMoves = atackingpiece[i].GetAvailableMoves(ref chessPieces, TILE_COUNT_X, TILE_COUNT_Y);
            for (int b = 0; b < pieceMoves.Count; b++)
            {
                currentAvailableMoves.Add(pieceMoves[b]);
            }
        }
        //Are we in check right now
        if (ContainsValidMove(ref currentAvailableMoves, new Vector2Int(targetKing.CurrentX, targetKing.CurrentY)))
        {
            //King is under Attack, moe something to help him
            for (int i = 0; i < defendingPieces.Count; i++)
            {
                List<Vector2Int> defendingmoves = defendingPieces[i].GetAvailableMoves(ref chessPieces, TILE_COUNT_X, TILE_COUNT_Y);
                SimulateMoveForSInglePIece(defendingPieces[i], ref defendingmoves, targetKing);

                if (defendingmoves.Count != 0)
                    return false;

            }
            return true;
        }
        return false;
    }

    //Operations
    private Vector2Int LookupTileIndex(GameObject hitinfo)
    {
        for (int x = 0; x < TILE_COUNT_X; x++)
            for (int y = 0; y < TILE_COUNT_Y; y++)
                if (tiles[x, y] == hitinfo)
                    return new Vector2Int(x, y);
        return -Vector2Int.one; //Invalid
    }

    private void MoveTo(int originalX, int originalY, int x, int y)
    {
        ChessPiece cp = chessPieces[originalX, originalY];
        Vector2Int previousPosition = new Vector2Int(originalX, originalY);
        //Anyother piece in the taget position
        if (chessPieces[x, y] != null)
        {
            ChessPiece ocp = chessPieces[x, y];
            if (cp.team == ocp.team)
            {
                return;
            }
            //if its the enemy team
            if (ocp.team == 0)//white
            {
                if (ocp.type == ChessPieceType.King)
                    CheckMate(1);
                deadWhites.Add(ocp);
                ocp.SetScale(Vector3.one * deathSize);
                ocp.SetPosition(new Vector3(8 * tileSize, yOffset, -1 * tileSize)
                    - bounds + new Vector3(tileSize / 2, 0, tileSize / 2) + (Vector3.forward * deathSpacing) * deadWhites.Count);
            }
            else
            {
                if (ocp.type == ChessPieceType.King)
                    CheckMate(0);
                deadBlacks.Add(ocp);
                ocp.SetScale(Vector3.one * deathSize);
                ocp.SetPosition(new Vector3(-1 * tileSize, yOffset, 8 * tileSize)
                    - bounds + new Vector3(tileSize / 2, 0, tileSize / 2) + (Vector3.back * deathSpacing) * deadBlacks.Count);
            }

        }
        chessPieces[x, y] = cp;
        chessPieces[previousPosition.x, previousPosition.y] = null;
        PositionSinglePieces(x, y);
        isWhiteTurn = !isWhiteTurn;
        if (localGame)
            currentTeam = (currentTeam == 0) ? 1 : 0;

        moveList.Add(new Vector2Int[] { previousPosition, new Vector2Int(x, y) });

        ProcessSpecialMove();

        if (currentlyDragging)
            currentlyDragging = null;
        removeHighlightTiles();


        if (CheckForCheckMate())
            CheckMate(cp.team);

        return;
    }
    private bool ContainsValidMove(ref List<Vector2Int> moves, Vector2 pos)
    {
        for (int i = 0; i < moves.Count; i++)
        {
            if (moves[i].x == pos.x && moves[i].y == pos.y)
                return true;
        }
        return false;
    }

    private void RegisterEvents()
    {
        NetUtility.S_WELCOME += OnWelcomeServer;
        NetUtility.S_MAKE_MOVE += OnMakeServer;
        NetUtility.S_REMATCH += OnRematchServer;
        NetUtility.S_MESSAGE += OnMessageServer;


        NetUtility.C_WELCOME += OnWelcomeClient;
        NetUtility.C_START_GAME += OnStartGameClient;
        NetUtility.C_MAKE_MOVE += onmakeMoveClient;
        NetUtility.C_REMATCH += OnRematchClient;
        NetUtility.C_MESSAGE += OnMessageClient;

        gameui.Instance.SetLocalGame += OnsetLocalGame;
    }

    private void UnregisterEvents()
    {

        NetUtility.S_WELCOME -= OnWelcomeServer;
        NetUtility.S_MAKE_MOVE -= OnMakeServer;
        NetUtility.S_REMATCH -= OnRematchServer;
        NetUtility.S_MESSAGE -= OnMessageServer;

        NetUtility.C_WELCOME -= OnWelcomeClient;
        NetUtility.C_START_GAME -= OnStartGameClient;
        NetUtility.C_MAKE_MOVE -= onmakeMoveClient;
        NetUtility.C_REMATCH -= OnRematchClient;
        NetUtility.C_MESSAGE -= OnMessageClient;

        gameui.Instance.SetLocalGame -= OnsetLocalGame;
    }
    //Server


    private void OnsetLocalGame(bool obj)
    {
        playerCount = -1;
        currentTeam = -1;
        localGame = obj;
    }

    private void OnWelcomeServer(NetMessage msg, NetworkConnection cnn)
    {
        NetWelcome nw = msg as NetWelcome;

        nw.Assignedteam = ++playerCount;

        server.Instance.SendToClient(cnn, nw);

        if (playerCount == 1)
        {
            server.Instance.Broadcast(new NetStartGame());
        }
    }

    private void OnMakeServer(NetMessage msg, NetworkConnection cnn)
    {
        NetMakeMove nm = msg as NetMakeMove;

        server.Instance.Broadcast(nm);
    }

    private void OnRematchServer(NetMessage msg, NetworkConnection cnn)
    {
        server.Instance.Broadcast(msg);
    }
    private void OnMessageServer(NetMessage msg, NetworkConnection cnn)
    {
        SendMessage mg = msg as SendMessage;

        server.Instance.Broadcast(mg);
    }
    private void OnMessageClient(NetMessage msg)
    {
        SendMessage mg = msg as SendMessage;

        Debug.Log("on message client " + mg);
    }


    //Client
    private void onmakeMoveClient(NetMessage msg)
    {
        NetMakeMove nw = msg as NetMakeMove;

        Debug.Log($" {nw.teamId} + {nw.originalX} + {nw.originalY}:-> {nw.destinationX} + {nw.destinationY}");

        if (nw.teamId != currentTeam)
        {
            ChessPiece target = chessPieces[nw.originalX, nw.originalY];

            availableMoves = target.GetAvailableMoves(ref chessPieces, TILE_COUNT_X, TILE_COUNT_Y);
            speciaMove = target.GetSpecialMoves(ref chessPieces, ref moveList, ref availableMoves);

            MoveTo(nw.originalX, nw.originalY, nw.destinationX, nw.destinationY);
        }

    }
    private void OnWelcomeClient(NetMessage obj)
    {
        NetWelcome nw = obj as NetWelcome;

        currentTeam = nw.Assignedteam;

        Debug.Log("My asigned team is " + nw.Assignedteam);
        if (localGame && currentTeam == 0)
        {
            server.Instance.Broadcast(new NetStartGame());
        }
    }
    private void OnStartGameClient(NetMessage obj)
    {
        gameui.Instance.ChangeCamera((currentTeam == 0) ? CamerAngle.whiteTeam : CamerAngle.blackTeam);
    }
    private void OnRematchClient(NetMessage msg)
    {
        NetReamtch rm = msg as NetReamtch;

        playerRematch[rm.teamId] = rm.wantRematch == 1;

        if (rm.teamId != currentTeam)
        {
            reamtchIndicator.transform.GetChild((rm.wantRematch == 1) ? 0 : 1).gameObject.SetActive(true);
            if (rm.wantRematch != 1)
                rematchButton.interactable = false;
        }

        if (playerRematch[0] && playerRematch[1])
            GameReset();
    }

    private void ShutdownServer()
    {
        client.Instance.Shutdown();
        server.Instance.Shutdown();
    }




}
