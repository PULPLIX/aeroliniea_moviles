package com.example.aerolinea.Model




class model {
    companion object Singleton{
        private var usuarios:MutableMap<String, Usuario> = mutableMapOf()

        init {
            // Aqui se inicializan las variables y metodos
            val usuario:Usuario = Usuario("12","1234","David","Aguilar","davidCorderoAguilar@gmail.com","abr 15 2020","Heredia Centro","4737383","898989","1");
            usuarios.put("1",usuario)
        }

        fun addUser(key:String, usuario:Usuario):Boolean{
            usuarios.put(key,usuario)
            return true
        }

        fun findUser(key: String):Usuario?{
            return usuarios.get(key)
        }
        fun editUser(usuario: Usuario){
            usuarios.put(usuario.nombre,usuario)
        }

        fun confirmUser(key: String,password: String):Boolean{
            if (usuarios.get(key)?.contrasena.equals(password)){
                return true
            }
            return false
        }
    }

    init {
        // Aqui se inicializan las variables o lo que se necesite en model
    }

    fun getInstance():Singleton{
        return Singleton
    }

}