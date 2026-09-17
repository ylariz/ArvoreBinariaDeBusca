
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ArvoreBinariaDeBusca<Integer> arvore =
                new ArvoreBinariaDeBusca<Integer>();

            arvore.guardeUmItem(10);
            arvore.guardeUmItem(5);
            arvore.guardeUmItem(15);
            arvore.guardeUmItem(3);
            arvore.guardeUmItem(7);

            System.out.println(arvore);

            System.out.println("Tem o numero: "+ arvore.temOItem(7));
            System.out.println("Tem o numero: " + arvore.temOItem(20));
        }
        catch (Exception erro)
        {
            System.out.println(erro.getMessage());
        }
    }
}
