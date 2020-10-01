import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';

class LoginPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => LoginPageState();
}

enum FormType { login, register }

class LoginPageState extends State<LoginPage> {
  final formkey = new GlobalKey<FormState>();

  String _email = '';
  String _password = '';
  FormType _formType = FormType.login;

  bool validateLogin() {
    final form = formkey.currentState;
    if (form.validate()) {
      form.save();
      return true;
    } else {
      return false;
    }
  }

  void passwordReset() {
    print(_email);
    FirebaseAuth.instance.sendPasswordResetEmail(email: _email);
  }

  void validateandSubmit() async {
    var auth = FirebaseAuth.instance;
    if (validateLogin()) {
      try {
        if (_formType == FormType.login) {
          // UserCredential user = (await auth.signInWithEmailAndPassword(
          //     email: _email, password: _password));
          auth.signInWithPhoneNumber(_email, RecaptchaVerifier());
          final User user1 = auth.currentUser;
          final uid = user1.uid;
          print('Signed in: $uid');
        } else {
          UserCredential user = (await auth.createUserWithEmailAndPassword(
              email: _email, password: _password));
          final User user1 = auth.currentUser;
          final uid = user1.uid;
          print('registered user ${user.credential}');
          print('registered user $uid');
        }
      } catch (e) {
        print(e);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Flutter Logon Demo'),
        ),
        body: Container(
          padding: EdgeInsets.all(16),
          child: Form(
            key: formkey,
            child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: buildInputs() + buttons()),
          ),
        ));
  }

  List<Widget> buildInputs() {
    return [
      TextFormField(
        decoration: InputDecoration(labelText: 'Email'),
        validator: (value) => value.isEmpty ? 'Email cant be Empty' : null,
        onSaved: (value) => _email = value,
      ),
      TextFormField(
        decoration: InputDecoration(labelText: 'Password'),
        obscureText: true,
        // validator: (value) => value.isEmpty ? 'Password cant be Empty' : null,
        onSaved: (value) => _password = value,
      ),
    ];
  }

  List<Widget> buttons() {
    if (_formType == FormType.login) {
      return [
        RaisedButton(
          child: Text(
            'Login',
            style: TextStyle(fontSize: 20),
          ),
          onPressed: validateandSubmit,
        ),
        FlatButton(onPressed: register, child: Text('Create an account')),
        FlatButton(onPressed: passwordReset, child: Text('Forgot the password'))
      ];
    } else {
      return [
        RaisedButton(
          child: Text(
            'Create an account',
            style: TextStyle(fontSize: 20),
          ),
          onPressed: validateandSubmit,
        ),
        FlatButton(onPressed: login, child: Text('Have an Account')),
      ];
    }
  }

  void register() {
    formkey.currentState.reset();
    setState(() {
      _formType = FormType.register;
    });
  }

  void login() {
    formkey.currentState.reset();
    setState(() {
      _formType = FormType.login;
    });
  }
}
