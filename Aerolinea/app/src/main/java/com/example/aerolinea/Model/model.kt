package com.example.aerolinea.Model




class model {
    companion object Singleton{
        private var usuarios:MutableMap<String, Usuario> = mutableMapOf()

        init {
            // Aqui se inicializan las variables y metodos
            val usuario:Usuario = Usuario("12","davidCorderoAguilar@gmail.com","1","1","60331950","22548891","Heredia Centro","1");
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
            if (usuarios.get(key)?.password.equals(password)){
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