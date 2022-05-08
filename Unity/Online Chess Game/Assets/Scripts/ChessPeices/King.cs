
using System.Collections.Generic;
using UnityEngine;

public class King : ChessPiece
{
    public override List<Vector2Int> GetAvailableMoves(ref ChessPiece[,] board, int tileCountX, int tileCountY)
    {
        List<Vector2Int> r = new List<Vector2Int>();

        //Right
        if (CurrentX + 1 < tileCountX)
        {
            if (board[CurrentX + 1, CurrentY] == null)
                r.Add(new Vector2Int(CurrentX + 1, CurrentY));
            else if (board[CurrentX + 1, CurrentY].team != team)
                r.Add(new Vector2Int(CurrentX + 1, CurrentY));

            //top right
            if (CurrentY + 1 < tileCountY)
                if (board[CurrentX + 1, CurrentY + 1] == null)
                    r.Add(new Vector2Int(CurrentX + 1, CurrentY + 1));
                else if (board[CurrentX + 1, CurrentY + 1].team != team)
                    r.Add(new Vector2Int(CurrentX + 1, CurrentY + 1));
            //Bottom right
            if (CurrentY - 1 >= 0)
                if (board[CurrentX + 1, CurrentY - 1] == null)
                    r.Add(new Vector2Int(CurrentX + 1, CurrentY - 1));
                else if (board[CurrentX + 1, CurrentY - 1].team != team)
                    r.Add(new Vector2Int(CurrentX + 1, CurrentY - 1));
        }


        //Left
        if (CurrentX - 1 >= 0)
        {
            if (board[CurrentX - 1, CurrentY] == null)
                r.Add(new Vector2Int(CurrentX - 1, CurrentY));
            else if (board[CurrentX - 1, CurrentY].team != team)
                r.Add(new Vector2Int(CurrentX - 1, CurrentY));

            //top left
            if (CurrentY + 1 < tileCountY)
                if (board[CurrentX - 1, CurrentY + 1] == null)
                    r.Add(new Vector2Int(CurrentX - 1, CurrentY + 1));
                else if (board[CurrentX - 1, CurrentY + 1].team != team)
                    r.Add(new Vector2Int(CurrentX - 1, CurrentY + 1));
            //Bottom left
            if (CurrentY - 1 >= 0)
                if (board[CurrentX - 1, CurrentY - 1] == null)
                    r.Add(new Vector2Int(CurrentX - 1, CurrentY - 1));
                else if (board[CurrentX - 1, CurrentY - 1].team != team)
                    r.Add(new Vector2Int(CurrentX - 1, CurrentY - 1));
        }

        //Up
        if (CurrentY + 1 < tileCountY)
            if (board[CurrentX, CurrentY + 1] == null || board[CurrentX, CurrentY + 1].team != team)
                r.Add(new Vector2Int(CurrentX, CurrentY + 1));

        //Down
        if (CurrentY - 1 >= 0)
            if (board[CurrentX, CurrentY - 1] == null || board[CurrentX, CurrentY - 1].team != team)
                r.Add(new Vector2Int(CurrentX, CurrentY - 1));


        return r;
    }

    public override SpeciaMove GetSpecialMoves(ref ChessPiece[,] board, ref List<Vector2Int[]> moveList, ref List<Vector2Int> availableMoves)
    {
        SpeciaMove r = SpeciaMove.None;

        var kingMove = moveList.Find(m => m[0].x == 4 && m[0].y == ((team == 0) ? 0 : 7));//king
        var leftRook = moveList.Find(m => m[0].x == 0 && m[0].y == ((team == 0) ? 0 : 7));//leftrook
        var rightRook = moveList.Find(m => m[0].x == 7 && m[0].y == ((team == 0) ? 0 : 7));//rightrook

        if (kingMove == null && CurrentX == 4)
        {
            //white Team
            if (team == 0)
            {
                if (leftRook == null)
                    if (board[0, 0].type == ChessPieceType.Rook)
                        if (board[0, 0].team == 0)
                            if (board[3, 0] == null)
                                if (board[2, 0] == null)
                                    if (board[1, 0] == null)
                                    {
                                        availableMoves.Add(new Vector2Int(2, 0));
                                        r = SpeciaMove.Castling;
                                    }

                if (rightRook == null)
                    if (board[7, 0].type == ChessPieceType.Rook)
                        if (board[7, 0].team == 0)
                            if (board[5, 0] == null)
                                if (board[6, 0] == null)
                                {
                                    availableMoves.Add(new Vector2Int(6, 0));
                                    r = SpeciaMove.Castling;
                                }


            }
            else
            {
                if (leftRook == null)
                    if (board[0, 7].type == ChessPieceType.Rook)
                        if (board[0, 7].team == 1)
                            if (board[3, 7] == null)
                                if (board[2, 7] == null)
                                    if (board[1, 7] == null)
                                    {
                                        availableMoves.Add(new Vector2Int(2, 7));
                                        r = SpeciaMove.Castling;
                                    }

                if (rightRook == null)
                    if (board[7, 7].type == ChessPieceType.Rook)
                        if (board[7, 7].team == 1)
                            if (board[5, 7] == null)
                                if (board[6, 7] == null)
                                {
                                    availableMoves.Add(new Vector2Int(6, 7));
                                    r = SpeciaMove.Castling;
                                }
            }
        }

        return r;
    }
}
