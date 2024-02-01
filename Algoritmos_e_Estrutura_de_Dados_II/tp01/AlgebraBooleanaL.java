public class AlgebraBooleanaL {
        public static boolean and(String aux) { 
    boolean resultado = true;
        for (int i = 0; i < aux.length() && resultado; i++) {
            if (aux.charAt(i) == '0') {
                resultado = false;
        }
    }
    return resultado;
}

public static boolean or(String aux) { 
        boolean resultado = false;
            for (int i = 0; i < aux.length() && !resultado; i++) {
                if (aux.charAt(i) == '1') {
                    resultado = true;
        }
    }
    return resultado;
}

public static boolean not(String aux) {
        boolean resultado = true;
            if (aux.charAt(0) == '1') {
            resultado = false;
    }
    return resultado;
}

public static boolean expressao(String expres) {
        boolean resultado = false, resultaux = false;
    String aux = "";
    int pa = 0, pf = 0, n = 0, qpa = 0;

    n = expres.charAt(0) - '0';

        for (int i = 0; i < expres.length(); i++) {
            if (expres.charAt(i) != ' ' && expres.charAt(i) != ',') {
            aux += expres.charAt(i);
            }
        }
    expres = aux;
    aux = "";
        for (int i = 0; i < expres.length(); i++) { 
            if ((expres.charAt(i) >= 'A' && expres.charAt(i) <= ('A' + n - 1))) {
    aux += expres.charAt(expres.charAt(i) - 'A' + 1);
    } else {
        aux += expres.charAt(i);
    }
        }
    expres = aux;
        for (int i = 0; i < expres.length(); i++) {
            if (expres.charAt(i) == '(') {
    qpa++;
    }
        }
    int k = 0;
    while (k < qpa) { 
        pa = 0;
        pf = 0;
    boolean exp = false;
    for (int i = 0; i < expres.length() && !exp; i++) {
            if (expres.charAt(i) == '(') {
                pa = i;
        }else if (expres.charAt(i) == ')') {
            pf = i;
        }
        if (pa < pf) {
    exp = true;
        }
}

    aux = "";
        for (int j = pa + 1; j < pf; j++) {
    aux += expres.charAt(j);
    }
    switch (expres.charAt(pa - 1)) {
    case 'd': 
    resultaux = and(aux);
    aux = "";
        for (int j = 0; j < expres.length(); j++) { 
            if (j == (pa - 3)) {
                if (resultaux) {
                aux += '1';
            } else {
        aux += '0';
    }
            }
        if (j < (pa - 3) || j > pf) {
            aux += expres.charAt(j);
            }
        }
        break;
    case 'r':
    resultaux = or(aux);
    aux = "";
    for (int j = 0; j < expres.length(); j++) {
        if (j == (pa - 2)) {
            if (resultaux) {
        aux += '1';
        } else {
            aux += '0';
    }
        }
        if (j < (pa - 2) || j > pf) {
    aux += expres.charAt(j);
        }
    }
    break;
    case 't':
    resultaux = not(aux);
    aux = "";
        for (int j = 0; j < expres.length(); j++) {
            if (j == (pa - 3)) {
                if (resultaux) {
                aux += '1';
            } else {
                aux += '0';
            }
        }
        if (j < (pa - 3) || j > pf) {
            aux += expres.charAt(j);
        }
        }
        break;

    default:
break;
}

    if (aux != "") {
        expres = aux;
}

    k++;
}
    if (expres.charAt(n + 1) == '1') {
        resultado = true;
}
    return resultado;

}

public static void main(String[] args) {
    String x = "";

    boolean resultado = false;
    x = MyIO.readLine();

    if (x.charAt(0) == '0') {
        return;
    }

    while (true) {
    resultado = expressao(x);

    if (resultado) {
        MyIO.println(1);
    } else {
        MyIO.println(0);
    }

    x = MyIO.readLine();

    if (x.charAt(0) == '0') {
        return;
    }

        }
    }
}