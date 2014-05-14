from pygments import highlight
from pygments.lexers.jvm import JavaLexer
from pygments.formatters import HtmlFormatter

code = '''public class Main {
    public static void main(String[] args) {
        int a = 0, b = 1;
        for (int i = 0; i <= 10; i++) {
            System.out.println(a);
            int c = a;
            a = b;
            b += c;
        }
    }
}'''

print(highlight(code, JavaLexer(tabsize = 2, encoding = 'utf-8'), HtmlFormatter(linenos = True)))
