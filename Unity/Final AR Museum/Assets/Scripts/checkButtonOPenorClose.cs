using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class checkButtonOPenorClose : MonoBehaviour
{
    [SerializeField]
    private bool opened = false;
    [SerializeField]
    private Animator animator;

    private void Start()
    {
        animator= GetComponent<Animator>();
    }
    public void ButtonOpen()
    {
        if (!opened)
        {
            Debug.Log(opened);
            animator.SetTrigger("Open");
            opened = true;
        }
        else
        {
            animator.SetTrigger("Close");
            opened = false;
        }
    }
}

