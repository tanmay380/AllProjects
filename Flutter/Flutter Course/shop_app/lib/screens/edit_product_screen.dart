import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/products.dart';

import '../provider/product.dart';

class EditProductScreen extends StatefulWidget {
  EditProductScreen({Key? key}) : super(key: key);
  static const routeName = "/editscree";

  @override
  _EditProductScreenState createState() => _EditProductScreenState();
}

class _EditProductScreenState extends State<EditProductScreen> {
  final _focusPriceNode = FocusNode();
  final _focusDscNode = FocusNode();
  final _imageUrlFocus = FocusNode();
  final _imageUrlController = TextEditingController();
  String imageyrl = '';
  final _form = GlobalKey<FormState>();
  var _editiedPRoduct =
      Product(id: 'null', title: '', description: '', price: 0, imageUrl: '');

  var isinit = true;

  var initValues = {
    'title': '',
    'description': '',
    'price': '',
    'imageUrl': '',
  };

  @override
  void initState() {
    _imageUrlFocus.addListener(_updateImage);
    // TODO: implement initState
    super.initState();
  }

  void _updateImage() {
    if (!_imageUrlFocus.hasFocus) {
      setState(() {});
    }
  }

  @override
  void dispose() {
    _imageUrlFocus.removeListener(_updateImage);
    _focusDscNode.dispose();
    _focusPriceNode.dispose();
    _imageUrlFocus.dispose();
    super.dispose();
  }

  @override
  void didChangeDependencies() {
    if (isinit) {
      final productId = ModalRoute.of(context)!.settings.arguments as String;
      print(productId);
      if (productId != null) {
        print(productId);
        _editiedPRoduct =
            Provider.of<Products>(context, listen: false).findById(productId);
        initValues = {
          'title': _editiedPRoduct.title,
          'description': _editiedPRoduct.description,
          'price': _editiedPRoduct.price.toString(),
          //   'imageUrl': _editiedPRoduct.imageUrl,
          'imageUrl': ''
        };
        _imageUrlController.text = _editiedPRoduct.imageUrl;
      }
    }
    isinit = false;
    super.didChangeDependencies();
  }

  void saveForm() {
    _form.currentState!.save();
    if (_editiedPRoduct.id != null) {
      Provider.of<Products>(context, listen: false)
          .updateProduct(_editiedPRoduct.id, _editiedPRoduct);
    } else {
      Provider.of<Products>(context, listen: false).addProduct(_editiedPRoduct);
    }
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Edit Product"),
        actions: [
          IconButton(onPressed: saveForm, icon: Icon(Icons.save)),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Form(
          key: _form,
          child: ListView(
            children: [
              TextFormField(
                initialValue: initValues['title'],
                decoration: InputDecoration(labelText: "Title"),
                textInputAction: TextInputAction.next,
                onFieldSubmitted: (_) {
                  FocusScope.of(context).requestFocus(_focusPriceNode);
                },
                onSaved: (value) {
                  _editiedPRoduct = Product(
                    id: _editiedPRoduct.id,
                    isFavorite: _editiedPRoduct.isFavorite,
                    title: value.toString(),
                    description: _editiedPRoduct.description,
                    price: _editiedPRoduct.price,
                    imageUrl: _editiedPRoduct.imageUrl,
                  );
                },
              ),
              TextFormField(
                initialValue: initValues['price'],
                decoration: InputDecoration(labelText: "Price"),
                textInputAction: TextInputAction.next,
                keyboardType: TextInputType.number,
                focusNode: _focusPriceNode,
                onFieldSubmitted: (_) {
                  FocusScope.of(context).requestFocus(_focusDscNode);
                },
                onSaved: (value) {
                  _editiedPRoduct = Product(
                      id: _editiedPRoduct.id,
                      isFavorite: _editiedPRoduct.isFavorite,
                      title: _editiedPRoduct.title,
                      description: _editiedPRoduct.description,
                      price: double.parse(value.toString()),
                      imageUrl: _editiedPRoduct.imageUrl);
                },
              ),
              TextFormField(
                initialValue: initValues['description'],
                decoration: InputDecoration(labelText: "Discription"),
                keyboardType: TextInputType.multiline,
                focusNode: _focusDscNode,
                maxLines: 3,
                onSaved: (value) {
                  _editiedPRoduct = Product(
                      id: _editiedPRoduct.id,
                      isFavorite: _editiedPRoduct.isFavorite,
                      title: _editiedPRoduct.title,
                      description: value.toString(),
                      price: _editiedPRoduct.price,
                      imageUrl: _editiedPRoduct.imageUrl);
                },
              ),
              Row(
                crossAxisAlignment: CrossAxisAlignment.end,
                children: [
                  Container(
                    width: 100,
                    height: 100,
                    margin: EdgeInsets.only(top: 8, right: 10),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey, width: 1),
                    ),
                    child: _imageUrlController.text.isEmpty
                        ? Text("Enter A URL")
                        : FittedBox(
                            child: Image.network(_imageUrlController.text),
                            fit: BoxFit.cover,
                          ),
                  ),
                  Expanded(
                    child: TextFormField(
                      //   initialValue: initValues['imageUrl'],
                      decoration: InputDecoration(labelText: "Image URL"),
                      keyboardType: TextInputType.url,
                      textInputAction: TextInputAction.done,
                      focusNode: _imageUrlFocus,
                      onFieldSubmitted: (_) {
                        // setState(() {
                        //   imageyrl = value;
                        // });
                        saveForm();
                      },
                      controller: _imageUrlController,
                      onSaved: (value) {
                        _editiedPRoduct = Product(
                            id: _editiedPRoduct.id,
                            isFavorite: _editiedPRoduct.isFavorite,
                            title: _editiedPRoduct.title,
                            description: _editiedPRoduct.description,
                            price: _editiedPRoduct.price,
                            imageUrl: value.toString());
                      },
                    ),
                  )
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}