using System.Collections.Generic;
using UnityEngine;

public class Pawn : ChessPiece
{
    public override List<Vector2Int> GetAvailableMoves(ref ChessPiece[,] board, int tileCountX, int tileCountY)
    {
        List<Vector2Int> r = new List<Vector2Int>();

        int direction = (team == 0) ? 1 : -1;
        
        // One in front
        if(board[CurrentX,CurrentY + direction] == null)
        {
            r.Add(new Vector2Int(CurrentX, CurrentY + direction));
        }

        //Two in front
        if (board[CurrentX, CurrentY + direction] == null)
        {
            // white team
            if(team==0 && CurrentY ==1 && board[CurrentX, CurrentY + (direction * 2)]==null)
            {
                r.Add(new Vector2Int(CurrentX, CurrentY + (direction*2)));
            }
            //black team
            if (team == 1 && CurrentY == 6 && board[CurrentX, CurrentY + (direction * 2)]==null)
            {
                r.Add(new Vector2Int(CurrentX, CurrentY + (direction * 2)));
            }
        }
        //Kill Move
        if(CurrentX!= tileCountX - 1)
        {
            if(board[CurrentX+1,CurrentY + direction]!= null && board[CurrentX + 1, CurrentY + direction].team != team)
                {
                    r.Add(new Vector2Int(CurrentX + 1, CurrentY + direction));
                }
        }
        if (CurrentX != 0)
        {
            if (board[CurrentX - 1, CurrentY + direction] != null && board[CurrentX - 1, CurrentY + direction].team != team)
                {
                    r.Add(new Vector2Int(CurrentX - 1, CurrentY + direction));
                }
        }

        return r;
    }

    public override SpeciaMove GetSpecialMoves(ref ChessPiece[,] board, ref List<Vector2Int[]> moveList, ref List<Vector2Int> availableMoves)
    {
        int direction = (team == 0) ? 1 : -1;

        //Queening
        if((team==0 && CurrentY==6) || (team == 1 && CurrentY == 1))
        {
            return SpeciaMove.Promition;
        }

        //En passant
        if (moveList.Count > 0)
        {
            var lastmove = moveList[moveList.Count - 1];
            if(board[lastmove[1].x, lastmove[1].y].type == ChessPieceType.Pawn)
            {
                if(Mathf.Abs(lastmove[0].y - lastmove[1].y) == 2)
                {
                    if (board[lastmove[1].x, lastmove[1].y].team != team)
                    {
                        if(lastmove[1].y == CurrentY)
                        {
                            if(lastmove[1].x== CurrentX - 1)
                            {
                                availableMoves.Add(new Vector2Int(CurrentX-1, CurrentY+direction));
                                return SpeciaMove.Enpassant;
                            }
                            if (lastmove[1].x == CurrentX + 1)
                            {
                                availableMoves.Add(new Vector2Int(CurrentX + 1, CurrentY + direction));
                                return SpeciaMove.Enpassant;
                            }
                        }
                    }
                }
            }
        }

         

        return SpeciaMove.None;
    }
} 
