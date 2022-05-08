
using System;
using TMPro;
using UnityEngine;

public enum CamerAngle
{
    menu=0,
    whiteTeam=1,
    blackTeam=2
}

public class gameui : MonoBehaviour
{
    public static  gameui Instance { set; get; }

    public server server;
    public client client;

    [SerializeField] private Animator menuAnimator;
    [SerializeField] private TMP_InputField address;
    [SerializeField] private GameObject[] cameraAngles;

    public Action<bool> SetLocalGame;

    private void Awake()
    {
        Instance = this;
        RegisterEvents();

    }

    //Camera
    public void ChangeCamera(CamerAngle index)
    {
        for (int i = 0; i < cameraAngles.Length; i++)
        {
            cameraAngles[i].SetActive(false);
        }
        cameraAngles[(int)index].SetActive(true);
    }

    public void OnLocalGameButton()
    {
        menuAnimator.SetTrigger("ingame");
        SetLocalGame?.Invoke(true);
        server.Init(8007);
        client.Init("127.0.0.1", 8007);
    }
    public void OnOnlineGameButton()
    {
        server.Init(8007);

        menuAnimator.SetTrigger("OnlineMenu");
    }
    public void OnOnlineHostButton()
    {
        client.Init("127.0.0.1", 8007);
        SetLocalGame?.Invoke(false);
        menuAnimator.SetTrigger("HostMenu");
    }
    public void OnOnlineConnectButton()
    {
        SetLocalGame?.Invoke(false);
        client.Init(address.text, 8007);
    }
    public void OnOnlineBackButton()
    {
        menuAnimator.SetTrigger("StartMenu");    
    }
    public void OnHostBackButton()
    {
        server.Shutdown();
        client.Shutdown();
        menuAnimator.SetTrigger("OnlineMenu");
    }

    private void RegisterEvents()
    {
        NetUtility.C_START_GAME += OnStartGameClient;
    }

    private void OnStartGameClient(NetMessage obj)
    {

        menuAnimator.SetTrigger("ingame");
    }

    private void UnregisterEvents()
    {
        NetUtility.C_START_GAME -= OnStartGameClient;
    }

    internal void OnLeaveFromGameMenu()
    {
        ChangeCamera(CamerAngle.menu);
        menuAnimator.SetTrigger("StartMenu");
    }
}
