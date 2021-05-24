using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class testprefab : MonoBehaviour
{

    public GameObject prefab;
    private Vector2 touchpositon;
    void Start()
    {
        Instantiate(prefab, new Vector3(-2.49f,-1.45f,17.4f), Quaternion.identity);
        
        Portal.device = GameObject.FindWithTag("MainCamera");
    }


    void Update()
    {
         
    }
}
