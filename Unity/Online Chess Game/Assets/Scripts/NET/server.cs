
using System;
using Unity.Collections;
using Unity.Networking.Transport;
using UnityEngine;

public class server : MonoBehaviour
{
    public static server Instance { set; get; }

    private void Awake()
    {
        Instance = this;
    }

    public NetworkDriver driver;
    private NativeList<NetworkConnection> connection;

    private bool isActive = false;
    private const float keepAliveTickRate = 20.0f;
    private float lastKeepAlive;

    public Action connectionDropeed;

    //Methods
    public void Init(ushort port)
    {
        driver = NetworkDriver.Create();
        NetworkEndPoint endPoint = NetworkEndPoint.AnyIpv4;
        endPoint.Port = port;

        if (driver.Bind(endPoint) != 0)
        {
            Debug.Log("Unable to bind the port " + endPoint.Port);
            return;
        }
        else
        {
            driver.Listen();
            Debug.Log("Currecnlty lister on port " + endPoint.Port);
        }
        connection = new NativeList<NetworkConnection>(2, Allocator.Persistent);
        isActive = true;


    }
    public void Shutdown()
    {
        if (isActive)
        {
            driver.Dispose();
            connection.Dispose();
            isActive = false;
        }
    }
    public void OnDestroy()
    {
        Shutdown();
    }
    private void Update()
    {
        if (!isActive)
            return;

       KeepAlive();

        driver.ScheduleUpdate().Complete();
        CleanupConnections();
        AcceptNewConnection();
        UpdateMessagePump();
    }

    private void KeepAlive()
    {
        if (Time.time - lastKeepAlive > keepAliveTickRate)
        {
            lastKeepAlive = Time.time;
            Broadcast(new NetKeppAlive());
        }
    }

    private void CleanupConnections()
    {
        for (int i = 0; i < connection.Length; i++)
        {
            if (!connection[i].IsCreated)
            {
                connection.RemoveAtSwapBack(i);
                --i;
            }
        }
    }
    private void AcceptNewConnection()
    {
        NetworkConnection c;
        while ((c=driver.Accept())!=default(NetworkConnection))
        {
            connection.Add(c);
        }
    }
    private void UpdateMessagePump()
    {
        DataStreamReader stream;
        for (int i = 0; i < connection.Length; i++)
        {
            NetworkEvent.Type cmd;
            while ((cmd=driver.PopEventForConnection(connection[i], out stream)) != NetworkEvent.Type.Empty)
            {
                if (cmd == NetworkEvent.Type.Data)
                {
                    NetUtility.OnData(stream, connection[i], this);
                }
                else if(cmd== NetworkEvent.Type.Disconnect)
                {
                    Debug.Log("Client disconneted from server");
                    connection[i] = default(NetworkConnection);
                    connectionDropeed?.Invoke();
                    Shutdown();
                }
            }
        }
    }

    //server specific
    public void Broadcast(NetMessage msg)
    {
        for (int i = 0; i < connection.Length; i++)
        {
            if (connection[i].IsCreated)
            {
                Debug.Log($"Sending {msg.Code} to : {connection[i].InternalId}");
                SendToClient(connection[i], msg);
            }
        }
    }

    public void SendToClient(NetworkConnection networkConnection, NetMessage msg)
    {
        DataStreamWriter writer;
        driver.BeginSend(networkConnection, out writer);
        msg.Serialize(ref writer);
        driver.EndSend(writer);
    }
}
