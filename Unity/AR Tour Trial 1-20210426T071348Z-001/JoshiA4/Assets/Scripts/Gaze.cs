using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class Gaze : MonoBehaviour
{
	List<InfoBehaviour> infos = new List<InfoBehaviour>();


	[SerializeField] private GameObject M4A1;
	[SerializeField] private GameObject Ak47;
    [SerializeField] private GameObject gun;
     GameObject go;
	[SerializeField] private GameObject SpawnPoint1;
	[SerializeField] private GameObject SpawnPoint2;
    [SerializeField] private GameObject SpawnPoint3;

    void Start()
		{
			infos = FindObjectsOfType<InfoBehaviour>().ToList();
		}

	void Update()
	{
		if (Physics.Raycast(transform.position, transform.forward, out RaycastHit hit))
		{

			go = hit.collider.gameObject;
            Debug.Log("plus"+ hit.collider.gameObject.name);
			if (go.gameObject.tag == "hasInfo")
			{
                //Debug.Log(go.GetComponent<InfoBehaviour>()  + "Working");
				OpenInfo(go.GetComponent<InfoBehaviour>());
                Debug.Log("Open");
              
			}
        }
        else
        {
            CloseAll();
                     Debug.Log("Closing");
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
			Debug.Log("gun1Excess");

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
			Destroy(go);
			go = Instantiate(Ak47, SpawnPoint2.transform.position, SpawnPoint2.transform.rotation);
			Debug.Log("gun2Excess");
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
        Debug.Log("gun3Excess");
    }
    void OpenInfo(InfoBehaviour desiredInfo)
	{
		foreach(InfoBehaviour info in infos)
		{
            Debug.Log("desiredInfo" + desiredInfo);
            if (info == desiredInfo)
            {
                Debug.Log("Info");
                info.OpenInfo();
            }
            else
            {
                info.CloseInfo();
                Debug.Log("close");
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
