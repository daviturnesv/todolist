package br.com.daviturnesv.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    // Método para copiar propriedades não nulas de um objeto fonte para um objeto alvo
    public static void copyNonNullPorperties(Object source, Object target){
        // Copia as propriedades do source para o target, ignorando as propriedades nulas
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Método para obter os nomes das propriedades que são nulas no objeto fonte
    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

         PropertyDescriptor[] pds = src.getPropertyDescriptors();

         Set<String> emptyNames = new HashSet<>();

         // Itera sobre todas as propriedades do objeto fonte
         for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            // Se o valor da propriedade for nulo, adiciona o nome da propriedade ao conjunto emptyNames
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
         }
         // Converte o conjunto de nomes de propriedades nulas em um array de Strings
         String[] result = new String[emptyNames.size()];
         return emptyNames.toArray(result);
    }

}
