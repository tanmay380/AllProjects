using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class ChessBoard : MonoBehaviour
{
    [Header("Art stuff")]
    [SerializeField] private Material tilematerial;
    [SerializeField] private float tileSize = 1.0f;
    [SerializeField] private float yOffset = 0.2f;
    [SerializeField] private Vector3 boardCenter = Vector3.zero;

    [Header("Prefabs & Material")]
    [SerializeField] private GameObject[] prefabs;
    [SerializeField] private Material[] teamMaterials;


    // LOGIC 
    private ChessPiece[,] chessPieces;
    private const int TILE_COUNT_X = 8;
    private const int TILE_COUNT_Y = 8;
    private GameObject[,] tiles;
    private Camera currentCamera;
    private Vector2Int currentHover;
    private Vector3 bounds;

    private void Awake()
    {
        GenerateAllTiles(tileSize, TILE_COUNT_X, TILE_COUNT_Y);
        // SpawnSinglePiece(ChessPieceType.King, 0);
        SpawnAllPieces();
        PositionAllPieces();
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
        if (Physics.Raycast(ray, out info, 100, LayerMask.GetMask("Tile", "Hover")))
        {
            Vector2Int hitPosition = LookupTileIndex(info.transform.gameObject);
            print("Name of raycasted item is:- " + info.collider.gameObject.name);

            //If we're hivering a tile after not hovering any tiles
            if (currentHover == -Vector2Int.one)
            {
                currentHover = hitPosition;
                tiles[hitPosition.x, hitPosition.y].layer = LayerMask.NameToLayer("Hover");
            }

            // If we were laready hovering a tile, change the previous one
            if (currentHover != hitPosition)
            {
                tiles[currentHover.x, currentHover.y].layer = LayerMask.NameToLayer("Tile");
                currentHover = hitPosition;
                tiles[hitPosition.x, hitPosition.y].layer = LayerMask.NameToLayer("Hover");
            }
        }
        else
        {
            if (currentHover != -Vector2Int.one)
            {
                tiles[currentHover.x, currentHover.y].layer = LayerMask.NameToLayer("Tile");
                currentHover = -Vector2Int.one;
            }
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
        chessPieces[x, y].transform.position = GetTileCenter(x, y);
    }
    private Vector3 GetTileCenter(int x, int y)
    {
        return new Vector3(x * tileSize, yOffset, y * tileSize) - bounds + new Vector3(tileSize / 2, 0, tileSize / 2);
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
}
