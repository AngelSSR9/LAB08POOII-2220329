import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

        //a. Crear cadenas de manera aleatoria.
        List<String> cadena = Stream.generate(() ->{
            int longitud = generarNumeroAleatorio(1, 10);
            return Stream.generate(() -> {
                int index = generarNumeroAleatorio(0, banco.length() - 1);
                return banco.charAt(index);
            }).limit(longitud)
                    .map(Object::toString)
                    .collect(Collectors.joining());
        }).limit(10)
                .map(Object::toString)
                .toList();
        
        System.out.println(cadena);
        System.out.println("--------------------");

        //b. Contar cuántas cadenas vacías tiene la lista de cadenas.
        long cantVacias = cadena.stream().filter(String::isEmpty).count();
        System.out.println("Cantidad de cadenas vacias en la lista: " + cantVacias);

        //c. Contabilizar cuántas cadenas tienen longitud superior a 5.
        long cantidad = cadena.stream().filter(c -> c.length() > 5).count();
        System.out.println("Cadenas con mas de 5: " + cantidad);

        //d. Contabilizar cuántas cadenas comienzan con "s".
        long n = cadena.stream().filter(c -> c.startsWith("s")).count();
        System.out.println("Cadenas que empiezan con 's': " + n);

        //e. Eliminar todas las cadenas vacías de la lista.
        List<String> listaSinCadenasVacias = cadena.stream().filter(c -> !c.isEmpty()).toList();
        System.out.println("Lista sin cadenas vacias: " + listaSinCadenasVacias);

        //f. Filtra la lista anterior con cadena de más de 5 caracteres.
        List<String> cadenasFiltrada = cadena.stream().filter(c -> c.length() > 5).toList();
        System.out.println("Lista con cadenas de mas de 5 caract: " + cadenasFiltrada);

        // g. Convertir las palabras a mayúsculas y concatenarlos usando una coma ‘,’.
        String superCadena = cadenasFiltrada .stream().map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println("Supercadena: " + superCadena);

        System.out.println("----------------------------------");
        IntSummaryStatistics stats = IntStream.of(1,2,3,4,5,6,7,8,9,10).summaryStatistics();
        System.out.println("Cantidad de elementos: " + stats.getCount());
        System.out.println("Suma: " + stats.getSum());
        System.out.println("Mínimo: " + stats.getMin());
        System.out.println("Máximo: " + stats.getMax());
        System.out.println("Promedio: " + stats.getAverage());
    }

    public static int generarNumeroAleatorio(int minimo, int maximo) {
        if (minimo > maximo) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el valor máximo.");
        }

        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

}
