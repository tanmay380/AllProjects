using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Rendering;


public class interdimensionaltransport : MonoBehaviour
{

public Material[] materials;
    void Start()
    {
         foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int) CompareFunction.Equal);
            }
    }

    private void OnTriggerStay(Collider other) {
        if (other.name != "Main Camera")
        {
            return;
        }

        // Outside of other world 
        if (transform.position.z > other.transform.position.z)
        {
            Debug.Log("OUTSIDE OF OTHER WORLD");
            foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int) CompareFunction.Equal);
            }
        }//Inside other DImension
        else{
            Debug.Log("INSIDE OF OTHER WORLD");
             foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int) CompareFunction.NotEqual);
            }
        }
    }

    private void OnDestroy() {
         foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int) CompareFunction.NotEqual);
            }
    }

    void Update()
    {
        
    }
}
