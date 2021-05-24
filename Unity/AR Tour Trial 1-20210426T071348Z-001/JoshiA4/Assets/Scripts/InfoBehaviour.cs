using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InfoBehaviour : MonoBehaviour
{
	const float SPEED = 0.5f;
	[SerializeField]
	Transform SecionInfo;

	Vector3 desiredScale = Vector3.zero;

	internal List<InfoBehaviour> ToList()
	{
		throw new NotImplementedException();
	}

	// Update is called once per frame
	void Update()
    {
		SecionInfo.localScale = Vector3.Lerp(SecionInfo.localScale, desiredScale, Time.deltaTime * SPEED);
    }

	public void OpenInfo()
	{
		desiredScale = Vector3.one;
	}
	public void CloseInfo()
	{
		desiredScale = Vector3.zero;
	}

}
