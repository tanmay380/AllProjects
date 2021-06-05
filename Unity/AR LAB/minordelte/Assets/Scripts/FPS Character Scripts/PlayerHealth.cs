using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class PlayerHealth : NetworkBehaviour

{
	public const float maxhelth = 100f;
	[SyncVar(hook= "OnChangeHealth")]public float health = maxhelth;
	public RectTransform healthbar;
	private NetworkStartPosition[] spawnPoints; 

	void Start()
    {
       
    }

   public void TakeDamage(float damage)
	{
		if (!isServer)
		{ 
			return;
		}
		if (health > 0f) 
		{
			health -= damage;
			print("Health is " + health);
		}

        else { 
		
			print("changed scene ");
			Scene scene = SceneManager.GetActiveScene(); 
			SceneManager.LoadScene(scene.name);
		}

		
	}


	void OnChangeHealth(float health1)
    {
		healthbar.sizeDelta = new Vector2(health1 * 2, healthbar.sizeDelta.y);
	}


	

	


}
