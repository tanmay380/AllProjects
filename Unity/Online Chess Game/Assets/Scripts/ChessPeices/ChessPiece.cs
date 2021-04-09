using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public enum ChessPieceType
{
    None = 0,
    Pawn = 1,
    Rook = 2,
    Knight = 3,
    Bishop = 4,
    Queen = 5,
    King = 6
}

public class ChessPiece : MonoBehaviour
{
    public int team;
    public int CurrentX;
    public int CurrentY;
    public ChessPieceType type;

    private Vector3 desiredPosition;
    private Vector3 desiredScale;
}
