<%-- 
    Document   : login
    Created on : Mar 26, 2021, 5:42:03 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <title>Login</title>
    </head>
    <body style="background-color: #14648d">
        <div class="row" style="margin-top: 5%">
            <center>
                <div class="col-md-6">
                    <center>
                        <div class="card card-container">
                            <center>
                                <h4>Welcome to David´s Airline</h4>
                                <img id="profile-img" class="profile-img-card" src="/aerolinea/resources/images/logoSinLetras2.png" alt="Logo" width="200" height="200" class="d-inline-block align-text-top rounded "/>
                                <p></p>
                                <form class="form-signin col-md-4" method="POST" action="" >
                                    <input type="id" name="id" value="12" required autofocus id="inputEmail" class="form-control" placeholder="User">
                                    <input type="password" name="password" value="1234" required autocomplete="current-password" id="inputPassword" class="form-control" placeholder="Password" >
                                    <br>
                                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                                </form>
                            </center>
                        </div>
                    </center>
                </div>
            </center>
        </div>
    </body>
</html>
