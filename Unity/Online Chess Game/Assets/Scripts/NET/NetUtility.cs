using System;
using System.Collections;
using System.Collections.Generic;
using Unity.Networking.Transport;
using UnityEngine;

public enum OpCode
{
    KEEP_ALIVE = 1,
    WELCOME = 2,
    START_GAME = 3,
    MAKE_MOVE = 4,
    REMATCH = 5,
    MESSAGE=6
}
public static class NetUtility
{
    public static void OnData(DataStreamReader stream, NetworkConnection cnn, server server = null)
    {
        Debug.Log("Inside the on data fundtion");
        NetMessage msg = null;
        var opCode = (OpCode)stream.ReadByte();
        switch (opCode)
        {
            case OpCode.KEEP_ALIVE: msg = new NetKeppAlive(stream); break;
            case OpCode.WELCOME: msg = new NetWelcome(stream); break;
            case OpCode.START_GAME: msg = new NetStartGame(stream); break;
            case OpCode.MAKE_MOVE: msg = new NetMakeMove(stream); break;
            case OpCode.REMATCH: msg = new NetReamtch(stream); break;
            case OpCode.MESSAGE: msg = new SendMessage(stream); break;
            default:
                {
                    Debug.LogError(opCode);
                    Debug.LogError("Message received had no OpCode"); break;
                }
        }
        if (server != null)
            msg.ReceivedOnServer(cnn);
        else
            msg.ReceivedOnClient();
    }



    public static Action<NetMessage> C_KEEP_ALIVE;
    public static Action<NetMessage> C_WELCOME;
    public static Action<NetMessage> C_START_GAME;
    public static Action<NetMessage> C_MAKE_MOVE;
    public static Action<NetMessage> C_REMATCH;
    public static Action<NetMessage> C_MESSAGE;
    public static Action<NetMessage, NetworkConnection> S_KEEP_ALIVE;
    public static Action<NetMessage, NetworkConnection> S_WELCOME;
    public static Action<NetMessage, NetworkConnection> S_START_GAME;
    public static Action<NetMessage, NetworkConnection> S_MAKE_MOVE;
    public static Action<NetMessage, NetworkConnection> S_REMATCH;
    public static Action<NetMessage, NetworkConnection> S_MESSAGE;
}
