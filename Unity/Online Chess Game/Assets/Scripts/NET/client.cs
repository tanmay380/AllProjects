using System;
using System.Collections;
using System.Collections.Generic;
using Unity.Collections;
using Unity.Networking.Transport;
using UnityEngine;

public class client : MonoBehaviour
{
    public static client Instance { set; get; }
    private void Awake()
    {
        Instance = this;
    }


    public NetworkDriver driver;
    private NetworkConnection connection;

    private bool isActive = false;
    private const float keepAliveTickRate = 20.0f;
    private float lastKeepAlive;

    public Action connectionDropeed;

    public void Init(string ip, ushort port)
    {
        driver = NetworkDriver.Create();
        NetworkEndPoint endpoint = NetworkEndPoint.Parse(ip, port);

        connection = driver.Connect(endpoint);

        Debug.Log("Unable to bind the port " + endpoint.Address);

        isActive = true;

        RegisterToEvent();

    }
    public void Shutdown()
    {
        if (isActive)
        {
            UnregisterToevent();
            driver.Dispose();
            isActive = false;
            connection = default(NetworkConnection);
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

        driver.ScheduleUpdate().Complete();
        CheckAlive();

        UpdateMessagePump();
    }

    private void CheckAlive()
    {
        if (!connection.IsCreated && isActive)
        {
            Debug.Log("SOmething went wront, lost connection to servber");
            connectionDropeed?.Invoke();
            Shutdown();
        }
    }

    private void UpdateMessagePump()
    {
        DataStreamReader stream;
        NetworkEvent.Type cmd;
        while ((cmd = connection.PopEvent(driver, out stream))!=NetworkEvent.Type.Empty)
        {
            if (cmd == NetworkEvent.Type.Connect)
            {
                SendToServer(new NetWelcome());
                Debug.Log("we are connected");
            }
            else if (cmd == NetworkEvent.Type.Data)
            {
                  NetUtility.OnData(stream, default(NetworkConnection));
            }else if (cmd == NetworkEvent.Type.Disconnect)
            {
                Debug.Log("Client got disconneced from server");
                connection = default(NetworkConnection);
                connectionDropeed?.Invoke();
                Shutdown();
            }
        }
    }

    public void SendToServer(NetMessage msg)
    {
        DataStreamWriter writer;
        driver.BeginSend(connection, out writer);
         msg.Serialize(ref writer);
        driver.EndSend(writer);
    }
    private void RegisterToEvent()
    {
        NetUtility.C_KEEP_ALIVE += OnKeepAlive;
    }


    private void UnregisterToevent()
    {
         NetUtility.C_KEEP_ALIVE -= OnKeepAlive;
    }
    private void OnKeepAlive(NetMessage nm)
    {
        SendToServer(nm);
    }

}
