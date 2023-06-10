package cl.cokke.CrudPersonas.util;

import cl.cokke.CrudPersonas.dto.TelefonoDTO;
import cl.cokke.CrudPersonas.model.Telefono;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {

    public static List<TelefonoDTO> concatenaTelefono(List<Telefono> phones){
        List<TelefonoDTO> telefonos = new ArrayList<>();

        for(int i= 0;i < phones.size(); i++){
            TelefonoDTO telefono = new TelefonoDTO();
            String numero = (phones.get(i).getCountrycode().concat("-") + phones.get(i).getCitycode().concat("-") + phones.get(i).getNumber());
            telefono.setNumber(numero);
            telefonos.add(telefono);
        }

        return telefonos;

    }
}
