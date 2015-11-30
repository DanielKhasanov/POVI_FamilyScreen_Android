package com.povi.povifamilyselect;

/**
 * Created by Daniel on 11/27/2015.
 */
 class FamilyMember {
    private String _name;
    private String _email;
    private String _message;
    private String _image;

    public FamilyMember(String name, String email, String message, String image) {
        _name = name;
        _email = email;
        _message = message;
        _image = image;

    }

    public FamilyMember(String name, String email, String message) {
        _name = name;
        _email = email;
        _message = message;
        _image = Character.toString(name.charAt(0)).toUpperCase() + "_icon.png";
    }

    protected String name(){
        return _name;
    }

    protected String email(){
        return _email;
    }

    protected String message(){
        return _message;
    }
}
