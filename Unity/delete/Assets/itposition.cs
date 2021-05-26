using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class itposition : MonoBehaviour
{
    
    // Update is called once per frame
    void Update()
    {
        Vector3 cubepos = transform.position;
        print(cubepos.ToString());
    }
}
