
using Unity.Networking.Transport;

public class NetKeppAlive : NetMessage
{
    public NetKeppAlive()
    {
        Code = OpCode.KEEP_ALIVE;
    }
    public NetKeppAlive(DataStreamReader reader)
    {
        Code = OpCode.KEEP_ALIVE;
        DeSerialize(reader);
    }
    public override void Serialize(ref DataStreamWriter writer)
    {
        writer.WriteByte((byte) Code);
    }
    public override void DeSerialize( DataStreamReader reader)
    {

    }
    public override void ReceivedOnClient()
    {
        NetUtility.C_KEEP_ALIVE?.Invoke(this);
    }
    public override void ReceivedOnServer(NetworkConnection cnn)
    {
        NetUtility.S_KEEP_ALIVE?.Invoke(this, cnn);
    }
}
