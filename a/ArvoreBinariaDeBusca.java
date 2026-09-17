import java.lang.reflect.*;

public class ArvoreBinariaDeBusca<X extends Comparable<X>> implements Cloneable {
    private class No {
        private No esq;
        private X info;
        private No dir;

        public No(No e, X i, No d) {
            this.esq = e;
            this.info = i;
            this.dir = d;
        }

        public No(X i) {
            this.esq = null;
            this.info = i;
            this.dir = null;
        }

        public No getEsq() {
            return this.esq;
        }

        public X getInfo() {
            return this.info;
        }

        public No getDir() {
            return this.dir;
        }

        public void setEsq(No e) {
            this.esq = e;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setDir(No d) {
            this.dir = d;
        }
    } // fim da classe No

    private No raiz;

    private X meuCloneDeX(X x) {
        X ret = null;

        try {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object[] parms = null;

            ret = (X) metodo.invoke(x, parms);
        } catch (NoSuchMethodException erro) {
        }

        catch (IllegalAccessException erro) {
        }

        catch (InvocationTargetException erro) {
        }

        return ret;
    }

    public void guardeUmItem(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        if (i instanceof Cloneable)
            i = this.meuCloneDeX(i);

        if (this.raiz == null) {
            this.raiz = new No(i);
            return;
        }

        No atual = this.raiz;

        for (;;) // forever
        {
            int comparacao = i.compareTo(atual.getInfo());

            if (comparacao == 0)
                throw new Exception("Elemento repetido");

            if (comparacao < 0) {
                if (atual.getEsq() == null) {
                    atual.setEsq(new No(i));
                    return;
                } else
                    atual = atual.getEsq();
            } else // comparacao>0
            {
                if (atual.getDir() == null) {
                    atual.setDir(new No(i));
                    return;
                } else
                    atual = atual.getDir();
            }
        }
    }

    public boolean temOItem(X i) throws Exception {
        if (i == null)
            throw new Exception("Informacao ausente");

        No atual = this.raiz;

        while (atual != null) {
            int comparacao = i.compareTo(atual.getInfo());

            if (comparacao == 0)
                return true;

            if (comparacao < 0)
                atual = atual.getEsq();
            else
                atual = atual.getDir();
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        ArvoreBinariaDeBusca<X> arv = (ArvoreBinariaDeBusca<X>) obj;

        return equals(this.raiz, arv.raiz);
    }

    private boolean equals(No raizArv1, No raizArv2) {
        if (raizArv1 == null && raizArv2 == null)
            return true;

        if (raizArv1 == null && raizArv2 != null)
            return false;

        if (raizArv1 != null && raizArv2 == null)
            return false;

        if (!raizArv1.getInfo().equals(raizArv2.getInfo()))
            return false;

        if (!equals(raizArv1.getEsq(), raizArv2.getEsq()))
            return false;

        if (!equals(raizArv1.getDir(), raizArv2.getDir()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 666;

        ret = ret * 7 + hashCode(this.raiz);

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    private int hashCode(No no) {
        if (no == null)
            return 0;

        int ret = 666;

        ret = ret * 7 + no.getInfo().hashCode();
        ret = ret * 7 + hashCode(no.getEsq());
        ret = ret * 7 + hashCode(no.getDir());

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    @Override
    public String toString() {
        return toString(this.raiz);
    }

    private String toString(No no) {
        if (no == null)
            return "";

        String ret = "";

        ret += toString(no.getEsq());
        ret += no.getInfo() + " ";
        ret += toString(no.getDir());

        return ret;
    }

    public ArvoreBinariaDeBusca(ArvoreBinariaDeBusca<X> modelo)
            throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.raiz = copia(modelo.raiz);
    }

    public ArvoreBinariaDeBusca() {
        this.raiz = null;
    }

    private No copia(No no) {
        if (no == null)
            return null;

        return new No(copia(no.getEsq()),
                no.getInfo(),
                copia(no.getDir()));
    }

    @Override
    public Object clone() {
        ArvoreBinariaDeBusca<X> ret = null;
        try {
            ret = new ArvoreBinariaDeBusca<X>(this);
        } catch (Exception erro) {
        }

        return ret;
    }

} // fim da classe ArvoreBinariaDeBusca
