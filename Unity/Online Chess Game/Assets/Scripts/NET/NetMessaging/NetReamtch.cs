using System.Collections;
using System.Collections.Generic;
using Unity.Networking.Transport;
using UnityEngine;

public class NetReamtch : NetMessage
{

    public int teamId;
    public byte wantRematch;

    public NetReamtch()
    {
        Code = OpCode.REMATCH;
    }
    public NetReamtch(DataStreamReader reader)
    {
        Code = OpCode.REMATCH;
        DeSerialize(reader);
    }

    public override void Serialize(ref DataStreamWriter writer)
    {
        writer.WriteByte((byte)Code);

        writer.WriteInt(teamId);
        writer.WriteByte(wantRematch);
    }
    public override void DeSerialize(DataStreamReader reader)
    {
        teamId = reader.ReadInt();
        wantRematch = reader.ReadByte();
    }

    public override void ReceivedOnClient()
    {
        NetUtility.C_REMATCH?.Invoke(this);
    }
    public override void ReceivedOnServer(NetworkConnection cnn)
    {
        NetUtility.S_REMATCH?.Invoke(this, cnn);
    }
}
