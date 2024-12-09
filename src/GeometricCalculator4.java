import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

interface Operacion {
    double calcular();
}

abstract class FiguraGeometrica implements Operacion {
    abstract double calcularArea();
    abstract double calcularPerimetro();

    @Override
    public double calcular() {
        return 0;
    }
}


class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}


class Cuadrado extends FiguraGeometrica {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    double calcularArea() {
        return lado * lado;
    }

    @Override
    double calcularPerimetro() {
        return 4 * lado;
    }
}


class Triangulo extends FiguraGeometrica {
    private double base;
    private double altura;
    private double lado1, lado2, lado3;

    public Triangulo(double base, double altura, double lado1, double lado2, double lado3) {
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    @Override
    double calcularArea() {
        return 0.5 * base * altura;
    }

    @Override
    double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }
}

class Rectangulo extends FiguraGeometrica {
    private double largo;
    private double ancho;

    public Rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }

    @Override
    double calcularArea() {
        return largo * ancho;
    }

    @Override
    double calcularPerimetro() {
        return 2 * (largo + ancho);
    }
}


class Pentagono extends FiguraGeometrica {
    private double lado;
    private double apotema;

    public Pentagono(double lado, double apotema) {
        this.lado = lado;
        this.apotema = apotema;
    }

    @Override
    double calcularArea() {
        return (5 * lado * apotema) / 2;
    }

    @Override
    double calcularPerimetro() {
        return 5 * lado;
    }
}


class Potencia implements Operacion {
    private double base;
    private int exponente;

    public Potencia(double base, int exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    @Override
    public double calcular() {
        return calcularPotencia(base, exponente);
    }

    private double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente > 0) {
            return base * calcularPotencia(base, exponente - 1);
        } else {
            return 1 / calcularPotencia(base, -exponente);
        }
    }
}


public class GeometricCalculator4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        while (true) {
            try {
                System.out.println("Seleccione una figura:");
                System.out.println("1. Círculo");
                System.out.println("2. Cuadrado");
                System.out.println("3. Triángulo");
                System.out.println("4. Rectángulo");
                System.out.println("5. Pentágono");
                System.out.println("6. Potencia");
                System.out.println("7. Salir");

                int choice = scanner.nextInt();

                if (choice == 7) {
                    System.out.println("Saliendo...");
                    break;
                }

                String result = "";
                switch (choice) {
                    case 1:
                        result = handleFigura(scanner, new Circulo(getDimension(scanner, "radio")), "Círculo");
                        break;
                    case 2:
                        result = handleFigura(scanner, new Cuadrado(getDimension(scanner, "lado")), "Cuadrado");
                        break;
                    case 3:
                        result = handleTriangulo(scanner);
                        break;
                    case 4:
                        result = handleFigura(scanner, new Rectangulo(getDimension(scanner, "largo"), getDimension(scanner, "ancho")), "Rectángulo");
                        break;
                    case 5:
                        result = handleFigura(scanner, new Pentagono(getDimension(scanner, "lado"), getDimension(scanner, "apotema")), "Pentágono");
                        break;
                    case 6:
                        result = handlePotencia(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida");
                        continue;
                }
                if (!result.isEmpty()) {
                    results.add(result);
                    System.out.println("Resultado: " + result);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        }

        System.out.println("Resultados almacenados:");
        for (String res : results) {
            System.out.println(res);
        }

        scanner.close();
    }

    private static String handleFigura(Scanner scanner, FiguraGeometrica figura, String tipoFigura) {
        System.out.println("Elija una operación:");
        System.out.println("1. Área");
        System.out.println("2. Perímetro");

        int operation = scanner.nextInt();

        switch (operation) {
            case 1:
                return "Área del " + tipoFigura + ": " + figura.calcularArea();
            case 2:
                return "Perímetro del " + tipoFigura + ": " + figura.calcularPerimetro();
            default:
                System.out.println("Operación no válida");
                return "";
        }
    }

    private static String handleTriangulo(Scanner scanner) {
        System.out.println("Ingrese la base:");
        double base = scanner.nextDouble();
        System.out.println("Ingrese la altura:");
        double altura = scanner.nextDouble();
        System.out.println("Ingrese el primer lado:");
        double lado1 = scanner.nextDouble();
        System.out.println("Ingrese el segundo lado:");
        double lado2 = scanner.nextDouble();
        System.out.println("Ingrese el tercer lado:");
        double lado3 = scanner.nextDouble();
        Triangulo triangulo = new Triangulo(base, altura, lado1, lado2, lado3);
        return handleFigura(scanner, triangulo, "Triángulo");
    }

    private static String handlePotencia(Scanner scanner) {
        System.out.println("Ingrese la base:");
        double base = scanner.nextDouble();
        System.out.println("Ingrese el exponente:");
        int exponente = scanner.nextInt();
        Potencia potencia = new Potencia(base, exponente);
        return "Resultado de " + base + " elevado a la potencia " + exponente + ": " + potencia.calcular();
    }

    private static double getDimension(Scanner scanner, String dimension) {
        System.out.println("Ingrese el " + dimension + ":");
        return scanner.nextDouble();
    }
}
