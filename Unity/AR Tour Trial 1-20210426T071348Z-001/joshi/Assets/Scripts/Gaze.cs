using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class Gaze : MonoBehaviour
{
	 public static List<InfoBehaviour>  infos = new List<InfoBehaviour>();


	[SerializeField] private GameObject M4A1;
	[SerializeField] private GameObject Ak47;
    [SerializeField] private GameObject gun;
	[SerializeField] private GameObject SpawnPoint1;
	GameObject go ;
	[SerializeField] private GameObject SpawnPoint2;
    [SerializeField] private GameObject SpawnPoint3;

    void Start()
		{
		}

	void Update()
	{
		
		if (Physics.Raycast(transform.position, transform.forward, out RaycastHit hit))
		{
			//print(infos.Count);
			 go = hit.collider.gameObject;
			if (go.gameObject.tag == "hasInfo")
			{
				Debug.Log("UPDATE + " + infos.Count);
				Debug.Log(go.GetComponent<InfoBehaviour>().name);
				OpenInfo(go.GetComponent<InfoBehaviour>());
              
			}
			
        }
        else
        {
            CloseAll();
        }

    }
		public void gun1()
		{
			/*if (go == null)
			{
				Debug.Log("gun1Excess");
				return;
			}
			else
			{
				Destroy(go);

				go = Instantiate(M4A1, SpawnPoint1.transform.position, Quaternion.identity);
			}*/
			Destroy(go);
			go = Instantiate(M4A1, SpawnPoint1.transform.position, Quaternion.identity);

		}

		public void gun2()
		{
			/*if (go == null)
			{
				return;
			}
			else
			{
				Destroy(go);
				go = Instantiate(Ak47, SpawnPoint2.transform.position, Quaternion.identity);
			}*/
			//Destroy(go);
			go = Instantiate(Ak47, SpawnPoint2.transform.position, SpawnPoint2.transform.rotation);
		}
    public void gun3()
    {
        /*if (go == null)
        {
            return;
        }
        else
        {
            Destroy(go);
            go = Instantiate(Ak47, SpawnPoint2.transform.position, Quaternion.identity);
        }*/
        Destroy(go);
        go = Instantiate(gun, SpawnPoint3.transform.position, SpawnPoint3.transform.rotation);
		//infos = FindObjectsOfType<InfoBehaviour>().ToList();
        
    }

    void OpenInfo(InfoBehaviour desiredInfo)
	{
		
		foreach(InfoBehaviour info in infos)
		{
            if (info == desiredInfo)
            {
				print("openInfo " + infos.Count);
                info.OpenInfo();
            }
            else
            {
                info.CloseInfo();
            }
            //Debug.Log("desiredInfo"+ desiredInfo);
        }
    }
	void CloseAll()
	{
		foreach (InfoBehaviour info in infos)
		{
			info.CloseInfo();
		}
	}
}
