using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class NetManager : NetworkManager
{
	public bool firstPlayerJoined;
	public override void OnServerAddPlayer(NetworkConnection conn, short playerControllerID)
	{
		GameObject playerObj = Instantiate (playerPrefab, Vector3.zero, Quaternion.identity);
		List<Transform> spawnPositons = NetworkManager.singleton.startPositions;
		if (!firstPlayerJoined)
		{
			firstPlayerJoined = true;
			playerObj.transform.position = spawnPositons[0].position;
		}
		else
		{
			playerObj.transform.position = spawnPositons[1].position;
		}
		NetworkServer.AddPlayerForConnection(conn, playerObj, playerControllerID);
	}

	void SetPortAndAddress()
	{
		NetworkManager.singleton.networkAddress = "localhost";
		NetworkManager.singleton.networkPort = 7777;
	}
	
	public void HostGame()
	{
		SetPortAndAddress();
		NetworkManager.singleton.StartHost();
	}

	public void JoinGame()
	{
		SetPortAndAddress();
		NetworkManager.singleton.StartClient();
	}
}
