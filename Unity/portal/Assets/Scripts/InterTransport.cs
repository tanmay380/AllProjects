using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Rendering;

public class InterTransport : MonoBehaviour
{
    public Material[] materials;

    void Start()
    {
        foreach (var mat in materials)
        {
            mat.SetInt("_StencilTest", (int)CompareFunction.NotEqual);
        }
    }

    private void OnTriggerStay(Collider other)
    {
        if (other.name != "Main Camera")
            return;
        //outside of other world
        if (transform.position.z > other.transform.position.z)
        {
            Debug.Log("ouside other wold");

            foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int)CompareFunction.Equal);
            }
        }
        //Inside other dimension
        else
        {
            Debug.Log("inside other wold");
            foreach (var mat in materials)
            {
                mat.SetInt("_StencilTest", (int)CompareFunction.NotEqual);
            }
        }

    }

    private void OnDestroy()
    {
        foreach (var mat in materials)
        {
            mat.SetInt("_StencilTest", (int)CompareFunction.NotEqual);
        }
    }

    void Update()
    {

    }
}
