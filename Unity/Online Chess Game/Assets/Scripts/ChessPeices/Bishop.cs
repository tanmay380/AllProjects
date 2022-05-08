
using System.Collections.Generic;
using UnityEngine;

public class Bishop : ChessPiece
{
    public override List<Vector2Int> GetAvailableMoves(ref ChessPiece[,] board, int tileCountX, int tileCountY)
    {
        List<Vector2Int> r = new List<Vector2Int>();

        //top right
        for (int x = CurrentX + 1, y = CurrentY + 1; x < tileCountX && y < tileCountY; x++, y++)
        {
            if (board[x, y] == null)
                r.Add(new Vector2Int(x, y));
            else
            {
                if (board[x, y].team != team)
                {
                    r.Add(new Vector2Int(x, y));
                }
                break;
            }
        }
        //top left
        for (int x = CurrentX - 1, y = CurrentY + 1; x >=0 && y < tileCountY; x--, y++)
        {
            if (board[x, y] == null)
                r.Add(new Vector2Int(x, y));
            else
            {
                if (board[x, y].team != team)
                {
                    r.Add(new Vector2Int(x, y));
                }
                break;
            }
        }
        //bottom right
        for (int x = CurrentX + 1, y = CurrentY - 1; x <tileCountX && y >=0; x++, y--)
        {
            if (board[x, y] == null)
                r.Add(new Vector2Int(x, y));
            else
            {
                if (board[x, y].team != team)
                {
                    r.Add(new Vector2Int(x, y));
                }
                break;
            }
        }
        //bottom left
        for (int x = CurrentX - 1, y = CurrentY - 1; x >= 0 && y >=0; x--, y--)
        {
            if (board[x, y] == null)
                r.Add(new Vector2Int(x, y));
            else
            {
                if (board[x, y].team != team)
                {
                    r.Add(new Vector2Int(x, y));
                }
                break;
            }
        }

        return r;
    }
}
