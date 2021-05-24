using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class Gaze1 : MonoBehaviour
{
    List<InfoBehaviour> infos= new List<InfoBehaviour>();

	[SerializeField] private GameObject Ak47;
	GameObject go ;
	[SerializeField] private GameObject SpawnPoint2;

    void Start()
    {
        
        gun2();
        infos = FindObjectsOfType<InfoBehaviour>().ToList();   
    }


    void Update()
    {
        if (Physics.Raycast(transform.position, transform.forward, out RaycastHit hit)){
                GameObject go = hit.collider.gameObject;
                if(go.CompareTag("hasInfo")){
                    print(go.gameObject.name);
                    OpenInfo(go.GetComponent<InfoBehaviour>());
                }
        }else{
            CloseAll();
        }
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
		}

    void OpenInfo( InfoBehaviour desiredinfo){
        print(infos.Count());
        foreach(InfoBehaviour info in infos){
            if(info == desiredinfo){
                print("OPENED "+info.gameObject.name);
                info.OpenInfo();
            }
            else{
                
                print("CLOSED " + info.gameObject.name);
                info.CloseInfo();
            }
        }
    }

    void CloseAll(){
        foreach(InfoBehaviour info in infos){
            info.CloseInfo();
        }
    }
}
