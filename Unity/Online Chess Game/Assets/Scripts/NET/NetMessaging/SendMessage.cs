
using Unity.Networking.Transport;
using UnityEngine;
public class SendMessage : NetMessage
{
    //public FixedString128Bytes ChatMessage { set; get; }

    public float ChatMessage;
    public SendMessage()
    {
        Code = OpCode.MESSAGE;
    }
    public SendMessage(DataStreamReader reader)
    {
        Code = OpCode.MESSAGE;
        Debug.Log(Code);
        DeSerialize(reader);
    }
    public override void Serialize(ref DataStreamWriter writer)
    {
        //writer.WriteFixedString128(ChatMessage);
        writer.WriteFloat(1.3f);
    }
    public override void DeSerialize(DataStreamReader reader)
    {
        //ChatMessage = reader.ReadFixedString128();
        ChatMessage = reader.ReadFloat();
    }

    public override void ReceivedOnClient()
    {
        NetUtility.C_MESSAGE?.Invoke(this);
        Debug.Log("CLIENT::" + ChatMessage);
    }
    public override void ReceivedOnServer(NetworkConnection cnn)
    {
        NetUtility.S_MESSAGE?.Invoke(this, cnn);
        Debug.Log("Server::" + ChatMessage);
    }

}
