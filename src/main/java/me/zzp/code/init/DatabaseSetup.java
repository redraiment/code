package me.zzp.code.init;

import java.util.HashSet;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import me.zzp.ar.DB;
import me.zzp.ar.Table;
import me.zzp.ar.ex.DBOpenException;

/**
 * Setup database connection pool.
 * Create tables automatically at the first time.
 */
public class DatabaseSetup implements ServletContextListener {
  private DB getDatabase() {
    try {
      DataSource pool = InitialContext.doLookup("java:/comp/env/jdbc/PostgreSQL");
      return DB.open(pool);
    } catch (NamingException e) {
      System.err.println("java:/comp/env/jdbc/PostgreSQL does not exist!");
      System.err.println(e.getMessage());
      throw new DBOpenException(e);
    }
  }
  
  private void createUserTable(final DB dbo) {
    dbo.tx(new Runnable() {
      @Override
      public void run() {
        Table User = dbo.createTable("users", "email text",
                                              "name text",
                                              "password text");
        User.create("email:", "redraiment@gmail.com",
                    "name:", "redraiment",
                    "password:", "123456");
      }
    });
  }
  
  private void createIssueTable(final DB dbo) {
    dbo.tx(new Runnable() {
      @Override
      public void run() {
        Table Issue = dbo.createTable("issues", "user_id integer",
                                                "parent_id integer",
                                                "name text",
                                                "description text",
                                                "sort integer");
        Issue.create("user_id:", 1,
                     "parent_id:", 0,
                     "name:", "首页",
                     "description:", "代码大全",
                     "sort:", 1);
      }
    });
  }
  
  private void createToolTable(final DB dbo) {
    dbo.tx(new Runnable() {
      @Override
      public void run() {
        Table Tool = dbo.createTable("tools", "user_id integer",
                                              "parent_id integer",
                                              "name text",
                                              "sort integer");
        Tool.create("user_id:", 1,
                    "parent_id:", 0,
                    "name:", "工具箱",
                    "sort:", 1);
      }
    });
  }

  private void createLexerTable(final DB dbo) {
    dbo.tx(new Runnable() {
      @Override
      public void run() {
        Table Lexer = dbo.createTable("lexers", "name text",
                                               "module text",
                                               "class text");
        Lexer.create("name:", "ABAP", "module:", "pygments.lexers.other", "class:", "ABAPLexer");
        Lexer.create("name:", "ANTLR With ActionScript Target", "module:", "pygments.lexers.parsers", "class:", "AntlrActionScriptLexer");
        Lexer.create("name:", "ANTLR With C# Target", "module:", "pygments.lexers.parsers", "class:", "AntlrCSharpLexer");
        Lexer.create("name:", "ANTLR With CPP Target", "module:", "pygments.lexers.parsers", "class:", "AntlrCppLexer");
        Lexer.create("name:", "ANTLR With Java Target", "module:", "pygments.lexers.parsers", "class:", "AntlrJavaLexer");
        Lexer.create("name:", "ANTLR With ObjectiveC Target", "module:", "pygments.lexers.parsers", "class:", "AntlrObjectiveCLexer");
        Lexer.create("name:", "ANTLR With Perl Target", "module:", "pygments.lexers.parsers", "class:", "AntlrPerlLexer");
        Lexer.create("name:", "ANTLR With Python Target", "module:", "pygments.lexers.parsers", "class:", "AntlrPythonLexer");
        Lexer.create("name:", "ANTLR With Ruby Target", "module:", "pygments.lexers.parsers", "class:", "AntlrRubyLexer");
        Lexer.create("name:", "ANTLR", "module:", "pygments.lexers.parsers", "class:", "AntlrLexer");
        Lexer.create("name:", "ActionScript 3", "module:", "pygments.lexers.web", "class:", "ActionScript3Lexer");
        Lexer.create("name:", "ActionScript", "module:", "pygments.lexers.web", "class:", "ActionScriptLexer");
        Lexer.create("name:", "Ada", "module:", "pygments.lexers.compiled", "class:", "AdaLexer");
        Lexer.create("name:", "ApacheConf", "module:", "pygments.lexers.text", "class:", "ApacheConfLexer");
        Lexer.create("name:", "AppleScript", "module:", "pygments.lexers.other", "class:", "AppleScriptLexer");
        Lexer.create("name:", "AspectJ", "module:", "pygments.lexers.jvm", "class:", "AspectJLexer");
        Lexer.create("name:", "Asymptote", "module:", "pygments.lexers.other", "class:", "AsymptoteLexer");
        Lexer.create("name:", "AutoIt", "module:", "pygments.lexers.other", "class:", "AutoItLexer");
        Lexer.create("name:", "Awk", "module:", "pygments.lexers.other", "class:", "AwkLexer");
        Lexer.create("name:", "BBCode", "module:", "pygments.lexers.text", "class:", "BBCodeLexer");
        Lexer.create("name:", "BUGS", "module:", "pygments.lexers.math", "class:", "BugsLexer");
        Lexer.create("name:", "Base Makefile", "module:", "pygments.lexers.text", "class:", "BaseMakefileLexer");
        Lexer.create("name:", "Bash Session", "module:", "pygments.lexers.shell", "class:", "BashSessionLexer");
        Lexer.create("name:", "Bash", "module:", "pygments.lexers.shell", "class:", "BashLexer");
        Lexer.create("name:", "Batchfile", "module:", "pygments.lexers.shell", "class:", "BatchLexer");
        Lexer.create("name:", "Befunge", "module:", "pygments.lexers.other", "class:", "BefungeLexer");
        Lexer.create("name:", "BlitzMax", "module:", "pygments.lexers.compiled", "class:", "BlitzMaxLexer");
        Lexer.create("name:", "Boo", "module:", "pygments.lexers.dotnet", "class:", "BooLexer");
        Lexer.create("name:", "Brainfuck", "module:", "pygments.lexers.other", "class:", "BrainfuckLexer");
        Lexer.create("name:", "Bro", "module:", "pygments.lexers.other", "class:", "BroLexer");
        Lexer.create("name:", "C#", "module:", "pygments.lexers.dotnet", "class:", "CSharpLexer");
        Lexer.create("name:", "C", "module:", "pygments.lexers.compiled", "class:", "CLexer");
        Lexer.create("name:", "C++", "module:", "pygments.lexers.compiled", "class:", "CppLexer");
        Lexer.create("name:", "CBM BASIC V2", "module:", "pygments.lexers.other", "class:", "CbmBasicV2Lexer");
        Lexer.create("name:", "CFEngine3", "module:", "pygments.lexers.other", "class:", "Cfengine3Lexer");
        Lexer.create("name:", "CMake", "module:", "pygments.lexers.text", "class:", "CMakeLexer");
        Lexer.create("name:", "COBOL", "module:", "pygments.lexers.compiled", "class:", "CobolLexer");
        Lexer.create("name:", "COBOLFree", "module:", "pygments.lexers.compiled", "class:", "CobolFreeformatLexer");
        Lexer.create("name:", "CSS", "module:", "pygments.lexers.web", "class:", "CssLexer");
        Lexer.create("name:", "CSS+Django/Jinja", "module:", "pygments.lexers.templates", "class:", "CssDjangoLexer");
        Lexer.create("name:", "CSS+Genshi Text", "module:", "pygments.lexers.templates", "class:", "CssGenshiLexer");
        Lexer.create("name:", "CSS+Lasso", "module:", "pygments.lexers.templates", "class:", "LassoCssLexer");
        Lexer.create("name:", "CSS+Mako", "module:", "pygments.lexers.templates", "class:", "MakoCssLexer");
        Lexer.create("name:", "CSS+Myghty", "module:", "pygments.lexers.templates", "class:", "MyghtyCssLexer");
        Lexer.create("name:", "CSS+PHP", "module:", "pygments.lexers.templates", "class:", "CssPhpLexer");
        Lexer.create("name:", "CSS+Ruby", "module:", "pygments.lexers.templates", "class:", "CssErbLexer");
        Lexer.create("name:", "CSS+Smarty", "module:", "pygments.lexers.templates", "class:", "CssSmartyLexer");
        Lexer.create("name:", "CUDA", "module:", "pygments.lexers.compiled", "class:", "CudaLexer");
        Lexer.create("name:", "Ceylon", "module:", "pygments.lexers.jvm", "class:", "CeylonLexer");
        Lexer.create("name:", "Cheetah", "module:", "pygments.lexers.templates", "class:", "CheetahLexer");
        Lexer.create("name:", "Clojure", "module:", "pygments.lexers.jvm", "class:", "ClojureLexer");
        Lexer.create("name:", "CoffeeScript", "module:", "pygments.lexers.web", "class:", "CoffeeScriptLexer");
        Lexer.create("name:", "Coldfusion HTML", "module:", "pygments.lexers.templates", "class:", "ColdfusionHtmlLexer");
        Lexer.create("name:", "Common Lisp", "module:", "pygments.lexers.functional", "class:", "CommonLispLexer");
        Lexer.create("name:", "Coq", "module:", "pygments.lexers.functional", "class:", "CoqLexer");
        Lexer.create("name:", "Croc", "module:", "pygments.lexers.agile", "class:", "CrocLexer");
        Lexer.create("name:", "Cython", "module:", "pygments.lexers.compiled", "class:", "CythonLexer");
        Lexer.create("name:", "D", "module:", "pygments.lexers.compiled", "class:", "DLexer");
        Lexer.create("name:", "DTD", "module:", "pygments.lexers.web", "class:", "DtdLexer");
        Lexer.create("name:", "Darcs Patch", "module:", "pygments.lexers.text", "class:", "DarcsPatchLexer");
        Lexer.create("name:", "Dart", "module:", "pygments.lexers.web", "class:", "DartLexer");
        Lexer.create("name:", "Debian Control file", "module:", "pygments.lexers.text", "class:", "DebianControlLexer");
        Lexer.create("name:", "Debian Sourcelist", "module:", "pygments.lexers.text", "class:", "SourcesListLexer");
        Lexer.create("name:", "Delphi", "module:", "pygments.lexers.compiled", "class:", "DelphiLexer");
        Lexer.create("name:", "Diff", "module:", "pygments.lexers.text", "class:", "DiffLexer");
        Lexer.create("name:", "Django/Jinja", "module:", "pygments.lexers.templates", "class:", "DjangoLexer");
        Lexer.create("name:", "Duel", "module:", "pygments.lexers.web", "class:", "DuelLexer");
        Lexer.create("name:", "Dylan session", "module:", "pygments.lexers.compiled", "class:", "DylanConsoleLexer");
        Lexer.create("name:", "Dylan", "module:", "pygments.lexers.compiled", "class:", "DylanLexer");
        Lexer.create("name:", "DylanLID", "module:", "pygments.lexers.compiled", "class:", "DylanLidLexer");
        Lexer.create("name:", "ECL", "module:", "pygments.lexers.other", "class:", "ECLLexer");
        Lexer.create("name:", "ERB", "module:", "pygments.lexers.templates", "class:", "ErbLexer");
        Lexer.create("name:", "Elixir iex session", "module:", "pygments.lexers.functional", "class:", "ElixirConsoleLexer");
        Lexer.create("name:", "Elixir", "module:", "pygments.lexers.functional", "class:", "ElixirLexer");
        Lexer.create("name:", "Embedded Ragel", "module:", "pygments.lexers.parsers", "class:", "RagelEmbeddedLexer");
        Lexer.create("name:", "Erlang erl session", "module:", "pygments.lexers.functional", "class:", "ErlangShellLexer");
        Lexer.create("name:", "Erlang", "module:", "pygments.lexers.functional", "class:", "ErlangLexer");
        Lexer.create("name:", "Evoque", "module:", "pygments.lexers.templates", "class:", "EvoqueLexer");
        Lexer.create("name:", "FSharp", "module:", "pygments.lexers.dotnet", "class:", "FSharpLexer");
        Lexer.create("name:", "Factor", "module:", "pygments.lexers.agile", "class:", "FactorLexer");
        Lexer.create("name:", "Fancy", "module:", "pygments.lexers.agile", "class:", "FancyLexer");
        Lexer.create("name:", "Fantom", "module:", "pygments.lexers.compiled", "class:", "FantomLexer");
        Lexer.create("name:", "Felix", "module:", "pygments.lexers.compiled", "class:", "FelixLexer");
        Lexer.create("name:", "Fortran", "module:", "pygments.lexers.compiled", "class:", "FortranLexer");
        Lexer.create("name:", "FoxPro", "module:", "pygments.lexers.foxpro", "class:", "FoxProLexer");
        Lexer.create("name:", "GAS", "module:", "pygments.lexers.asm", "class:", "GasLexer");
        Lexer.create("name:", "GLSL", "module:", "pygments.lexers.compiled", "class:", "GLShaderLexer");
        Lexer.create("name:", "Genshi Text", "module:", "pygments.lexers.templates", "class:", "GenshiTextLexer");
        Lexer.create("name:", "Genshi", "module:", "pygments.lexers.templates", "class:", "GenshiLexer");
        Lexer.create("name:", "Gettext Catalog", "module:", "pygments.lexers.text", "class:", "GettextLexer");
        Lexer.create("name:", "Gherkin", "module:", "pygments.lexers.other", "class:", "GherkinLexer");
        Lexer.create("name:", "Gnuplot", "module:", "pygments.lexers.other", "class:", "GnuplotLexer");
        Lexer.create("name:", "Go", "module:", "pygments.lexers.compiled", "class:", "GoLexer");
        Lexer.create("name:", "GoodData-CL", "module:", "pygments.lexers.other", "class:", "GoodDataCLLexer");
        Lexer.create("name:", "Gosu Template", "module:", "pygments.lexers.jvm", "class:", "GosuTemplateLexer");
        Lexer.create("name:", "Gosu", "module:", "pygments.lexers.jvm", "class:", "GosuLexer");
        Lexer.create("name:", "Groff", "module:", "pygments.lexers.text", "class:", "GroffLexer");
        Lexer.create("name:", "Groovy", "module:", "pygments.lexers.jvm", "class:", "GroovyLexer");
        Lexer.create("name:", "HTML", "module:", "pygments.lexers.web", "class:", "HtmlLexer");
        Lexer.create("name:", "HTML+Cheetah", "module:", "pygments.lexers.templates", "class:", "CheetahHtmlLexer");
        Lexer.create("name:", "HTML+Django/Jinja", "module:", "pygments.lexers.templates", "class:", "HtmlDjangoLexer");
        Lexer.create("name:", "HTML+Evoque", "module:", "pygments.lexers.templates", "class:", "EvoqueHtmlLexer");
        Lexer.create("name:", "HTML+Genshi", "module:", "pygments.lexers.templates", "class:", "HtmlGenshiLexer");
        Lexer.create("name:", "HTML+Lasso", "module:", "pygments.lexers.templates", "class:", "LassoHtmlLexer");
        Lexer.create("name:", "HTML+Mako", "module:", "pygments.lexers.templates", "class:", "MakoHtmlLexer");
        Lexer.create("name:", "HTML+Myghty", "module:", "pygments.lexers.templates", "class:", "MyghtyHtmlLexer");
        Lexer.create("name:", "HTML+PHP", "module:", "pygments.lexers.templates", "class:", "HtmlPhpLexer");
        Lexer.create("name:", "HTML+Smarty", "module:", "pygments.lexers.templates", "class:", "HtmlSmartyLexer");
        Lexer.create("name:", "HTML+Velocity", "module:", "pygments.lexers.templates", "class:", "VelocityHtmlLexer");
        Lexer.create("name:", "HTTP", "module:", "pygments.lexers.text", "class:", "HttpLexer");
        Lexer.create("name:", "Haml", "module:", "pygments.lexers.web", "class:", "HamlLexer");
        Lexer.create("name:", "Haskell", "module:", "pygments.lexers.functional", "class:", "HaskellLexer");
        Lexer.create("name:", "Hxml", "module:", "pygments.lexers.text", "class:", "HxmlLexer");
        Lexer.create("name:", "Hybris", "module:", "pygments.lexers.other", "class:", "HybrisLexer");
        Lexer.create("name:", "IDL", "module:", "pygments.lexers.math", "class:", "IDLLexer");
        Lexer.create("name:", "INI", "module:", "pygments.lexers.text", "class:", "IniLexer");
        Lexer.create("name:", "IRC logs", "module:", "pygments.lexers.text", "class:", "IrcLogsLexer");
        Lexer.create("name:", "Io", "module:", "pygments.lexers.agile", "class:", "IoLexer");
        Lexer.create("name:", "Ioke", "module:", "pygments.lexers.jvm", "class:", "IokeLexer");
        Lexer.create("name:", "JAGS", "module:", "pygments.lexers.math", "class:", "JagsLexer");
        Lexer.create("name:", "JSON", "module:", "pygments.lexers.web", "class:", "JsonLexer");
        Lexer.create("name:", "Jade", "module:", "pygments.lexers.web", "class:", "JadeLexer");
        Lexer.create("name:", "Java Server Page", "module:", "pygments.lexers.templates", "class:", "JspLexer");
        Lexer.create("name:", "Java", "module:", "pygments.lexers.jvm", "class:", "JavaLexer");
        Lexer.create("name:", "JavaScript", "module:", "pygments.lexers.web", "class:", "JavascriptLexer");
        Lexer.create("name:", "JavaScript+Cheetah", "module:", "pygments.lexers.templates", "class:", "CheetahJavascriptLexer");
        Lexer.create("name:", "JavaScript+Django/Jinja", "module:", "pygments.lexers.templates", "class:", "JavascriptDjangoLexer");
        Lexer.create("name:", "JavaScript+Genshi Text", "module:", "pygments.lexers.templates", "class:", "JavascriptGenshiLexer");
        Lexer.create("name:", "JavaScript+Lasso", "module:", "pygments.lexers.templates", "class:", "LassoJavascriptLexer");
        Lexer.create("name:", "JavaScript+Mako", "module:", "pygments.lexers.templates", "class:", "MakoJavascriptLexer");
        Lexer.create("name:", "JavaScript+Myghty", "module:", "pygments.lexers.templates", "class:", "MyghtyJavascriptLexer");
        Lexer.create("name:", "JavaScript+PHP", "module:", "pygments.lexers.templates", "class:", "JavascriptPhpLexer");
        Lexer.create("name:", "JavaScript+Ruby", "module:", "pygments.lexers.templates", "class:", "JavascriptErbLexer");
        Lexer.create("name:", "JavaScript+Smarty", "module:", "pygments.lexers.templates", "class:", "JavascriptSmartyLexer");
        Lexer.create("name:", "Julia console", "module:", "pygments.lexers.math", "class:", "JuliaConsoleLexer");
        Lexer.create("name:", "Julia", "module:", "pygments.lexers.math", "class:", "JuliaLexer");
        Lexer.create("name:", "Kconfig", "module:", "pygments.lexers.other", "class:", "KconfigLexer");
        Lexer.create("name:", "Koka", "module:", "pygments.lexers.functional", "class:", "KokaLexer");
        Lexer.create("name:", "Kotlin", "module:", "pygments.lexers.jvm", "class:", "KotlinLexer");
        Lexer.create("name:", "LLVM", "module:", "pygments.lexers.asm", "class:", "LlvmLexer");
        Lexer.create("name:", "Lasso", "module:", "pygments.lexers.web", "class:", "LassoLexer");
        Lexer.create("name:", "Lighttpd configuration file", "module:", "pygments.lexers.text", "class:", "LighttpdConfLexer");
        Lexer.create("name:", "Literate Haskell", "module:", "pygments.lexers.functional", "class:", "LiterateHaskellLexer");
        Lexer.create("name:", "LiveScript", "module:", "pygments.lexers.web", "class:", "LiveScriptLexer");
        Lexer.create("name:", "Logos", "module:", "pygments.lexers.compiled", "class:", "LogosLexer");
        Lexer.create("name:", "Logtalk", "module:", "pygments.lexers.other", "class:", "LogtalkLexer");
        Lexer.create("name:", "Lua", "module:", "pygments.lexers.agile", "class:", "LuaLexer");
        Lexer.create("name:", "MAQL", "module:", "pygments.lexers.other", "class:", "MaqlLexer");
        Lexer.create("name:", "MOOCode", "module:", "pygments.lexers.other", "class:", "MOOCodeLexer");
        Lexer.create("name:", "MXML", "module:", "pygments.lexers.web", "class:", "MxmlLexer");
        Lexer.create("name:", "Makefile", "module:", "pygments.lexers.text", "class:", "MakefileLexer");
        Lexer.create("name:", "Mako", "module:", "pygments.lexers.templates", "class:", "MakoLexer");
        Lexer.create("name:", "Mason", "module:", "pygments.lexers.templates", "class:", "MasonLexer");
        Lexer.create("name:", "Matlab session", "module:", "pygments.lexers.math", "class:", "MatlabSessionLexer");
        Lexer.create("name:", "Matlab", "module:", "pygments.lexers.math", "class:", "MatlabLexer");
        Lexer.create("name:", "MiniD", "module:", "pygments.lexers.agile", "class:", "MiniDLexer");
        Lexer.create("name:", "Modelica", "module:", "pygments.lexers.other", "class:", "ModelicaLexer");
        Lexer.create("name:", "Modula-2", "module:", "pygments.lexers.compiled", "class:", "Modula2Lexer");
        Lexer.create("name:", "MoinMoin/Trac Wiki markup", "module:", "pygments.lexers.text", "class:", "MoinWikiLexer");
        Lexer.create("name:", "Monkey", "module:", "pygments.lexers.compiled", "class:", "MonkeyLexer");
        Lexer.create("name:", "MoonScript", "module:", "pygments.lexers.agile", "class:", "MoonScriptLexer");
        Lexer.create("name:", "Mscgen", "module:", "pygments.lexers.other", "class:", "MscgenLexer");
        Lexer.create("name:", "MuPAD", "module:", "pygments.lexers.math", "class:", "MuPADLexer");
        Lexer.create("name:", "MySQL", "module:", "pygments.lexers.sql", "class:", "MySqlLexer");
        Lexer.create("name:", "Myghty", "module:", "pygments.lexers.templates", "class:", "MyghtyLexer");
        Lexer.create("name:", "NASM", "module:", "pygments.lexers.asm", "class:", "NasmLexer");
        Lexer.create("name:", "NSIS", "module:", "pygments.lexers.other", "class:", "NSISLexer");
        Lexer.create("name:", "Nemerle", "module:", "pygments.lexers.dotnet", "class:", "NemerleLexer");
        Lexer.create("name:", "NewLisp", "module:", "pygments.lexers.functional", "class:", "NewLispLexer");
        Lexer.create("name:", "Newspeak", "module:", "pygments.lexers.other", "class:", "NewspeakLexer");
        Lexer.create("name:", "Nginx configuration file", "module:", "pygments.lexers.text", "class:", "NginxConfLexer");
        Lexer.create("name:", "Nimrod", "module:", "pygments.lexers.compiled", "class:", "NimrodLexer");
        Lexer.create("name:", "NumPy", "module:", "pygments.lexers.math", "class:", "NumPyLexer");
        Lexer.create("name:", "OCaml", "module:", "pygments.lexers.functional", "class:", "OcamlLexer");
        Lexer.create("name:", "Objective-C", "module:", "pygments.lexers.compiled", "class:", "ObjectiveCLexer");
        Lexer.create("name:", "Objective-C++", "module:", "pygments.lexers.compiled", "class:", "ObjectiveCppLexer");
        Lexer.create("name:", "Objective-J", "module:", "pygments.lexers.web", "class:", "ObjectiveJLexer");
        Lexer.create("name:", "Octave", "module:", "pygments.lexers.math", "class:", "OctaveLexer");
        Lexer.create("name:", "Ooc", "module:", "pygments.lexers.compiled", "class:", "OocLexer");
        Lexer.create("name:", "Opa", "module:", "pygments.lexers.functional", "class:", "OpaLexer");
        Lexer.create("name:", "OpenEdge ABL", "module:", "pygments.lexers.other", "class:", "OpenEdgeLexer");
        Lexer.create("name:", "PHP", "module:", "pygments.lexers.web", "class:", "PhpLexer");
        Lexer.create("name:", "PL/pgSQL", "module:", "pygments.lexers.sql", "class:", "PlPgsqlLexer");
        Lexer.create("name:", "POVRay", "module:", "pygments.lexers.other", "class:", "PovrayLexer");
        Lexer.create("name:", "Perl", "module:", "pygments.lexers.agile", "class:", "PerlLexer");
        Lexer.create("name:", "PostScript", "module:", "pygments.lexers.other", "class:", "PostScriptLexer");
        Lexer.create("name:", "PostgreSQL SQL dialect", "module:", "pygments.lexers.sql", "class:", "PostgresLexer");
        Lexer.create("name:", "PostgreSQL console (psql)", "module:", "pygments.lexers.sql", "class:", "PostgresConsoleLexer");
        Lexer.create("name:", "PowerShell", "module:", "pygments.lexers.shell", "class:", "PowerShellLexer");
        Lexer.create("name:", "Prolog", "module:", "pygments.lexers.compiled", "class:", "PrologLexer");
        Lexer.create("name:", "Properties", "module:", "pygments.lexers.text", "class:", "PropertiesLexer");
        Lexer.create("name:", "Protocol Buffer", "module:", "pygments.lexers.other", "class:", "ProtoBufLexer");
        Lexer.create("name:", "Puppet", "module:", "pygments.lexers.other", "class:", "PuppetLexer");
        Lexer.create("name:", "PyPy Log", "module:", "pygments.lexers.text", "class:", "PyPyLogLexer");
        Lexer.create("name:", "Python 3", "module:", "pygments.lexers.agile", "class:", "Python3Lexer");
        Lexer.create("name:", "Python 3.0 Traceback", "module:", "pygments.lexers.agile", "class:", "Python3TracebackLexer");
        Lexer.create("name:", "Python Traceback", "module:", "pygments.lexers.agile", "class:", "PythonTracebackLexer");
        Lexer.create("name:", "Python console session", "module:", "pygments.lexers.agile", "class:", "PythonConsoleLexer");
        Lexer.create("name:", "Python", "module:", "pygments.lexers.agile", "class:", "PythonLexer");
        Lexer.create("name:", "QML", "module:", "pygments.lexers.web", "class:", "QmlLexer");
        Lexer.create("name:", "RConsole", "module:", "pygments.lexers.math", "class:", "RConsoleLexer");
        Lexer.create("name:", "REBOL", "module:", "pygments.lexers.other", "class:", "RebolLexer");
        Lexer.create("name:", "RHTML", "module:", "pygments.lexers.templates", "class:", "RhtmlLexer");
        Lexer.create("name:", "RPMSpec", "module:", "pygments.lexers.other", "class:", "RPMSpecLexer");
        Lexer.create("name:", "Racket", "module:", "pygments.lexers.functional", "class:", "RacketLexer");
        Lexer.create("name:", "Ragel in C Host", "module:", "pygments.lexers.parsers", "class:", "RagelCLexer");
        Lexer.create("name:", "Ragel in CPP Host", "module:", "pygments.lexers.parsers", "class:", "RagelCppLexer");
        Lexer.create("name:", "Ragel in D Host", "module:", "pygments.lexers.parsers", "class:", "RagelDLexer");
        Lexer.create("name:", "Ragel in Java Host", "module:", "pygments.lexers.parsers", "class:", "RagelJavaLexer");
        Lexer.create("name:", "Ragel in Objective C Host", "module:", "pygments.lexers.parsers", "class:", "RagelObjectiveCLexer");
        Lexer.create("name:", "Ragel in Ruby Host", "module:", "pygments.lexers.parsers", "class:", "RagelRubyLexer");
        Lexer.create("name:", "Ragel", "module:", "pygments.lexers.parsers", "class:", "RagelLexer");
        Lexer.create("name:", "Raw token data", "module:", "pygments.lexers.special", "class:", "RawTokenLexer");
        Lexer.create("name:", "Rd", "module:", "pygments.lexers.math", "class:", "RdLexer");
        Lexer.create("name:", "Redcode", "module:", "pygments.lexers.other", "class:", "RedcodeLexer");
        Lexer.create("name:", "RobotFramework", "module:", "pygments.lexers._robotframeworklexer", "class:", "RobotFrameworkLexer");
        Lexer.create("name:", "Ruby irb session", "module:", "pygments.lexers.agile", "class:", "RubyConsoleLexer");
        Lexer.create("name:", "Ruby", "module:", "pygments.lexers.agile", "class:", "RubyLexer");
        Lexer.create("name:", "Rust", "module:", "pygments.lexers.compiled", "class:", "RustLexer");
        Lexer.create("name:", "S", "module:", "pygments.lexers.math", "class:", "SLexer");
        Lexer.create("name:", "SCSS", "module:", "pygments.lexers.web", "class:", "ScssLexer");
        Lexer.create("name:", "SQL", "module:", "pygments.lexers.sql", "class:", "SqlLexer");
        Lexer.create("name:", "Sass", "module:", "pygments.lexers.web", "class:", "SassLexer");
        Lexer.create("name:", "Scala", "module:", "pygments.lexers.jvm", "class:", "ScalaLexer");
        Lexer.create("name:", "Scalate Server Page", "module:", "pygments.lexers.templates", "class:", "SspLexer");
        Lexer.create("name:", "Scaml", "module:", "pygments.lexers.web", "class:", "ScamlLexer");
        Lexer.create("name:", "Scheme", "module:", "pygments.lexers.functional", "class:", "SchemeLexer");
        Lexer.create("name:", "Scilab", "module:", "pygments.lexers.math", "class:", "ScilabLexer");
        Lexer.create("name:", "Shell Session", "module:", "pygments.lexers.shell", "class:", "ShellSessionLexer");
        Lexer.create("name:", "Smali", "module:", "pygments.lexers.dalvik", "class:", "SmaliLexer");
        Lexer.create("name:", "Smalltalk", "module:", "pygments.lexers.other", "class:", "SmalltalkLexer");
        Lexer.create("name:", "Smarty", "module:", "pygments.lexers.templates", "class:", "SmartyLexer");
        Lexer.create("name:", "Snobol", "module:", "pygments.lexers.other", "class:", "SnobolLexer");
        Lexer.create("name:", "SourcePawn", "module:", "pygments.lexers.other", "class:", "SourcePawnLexer");
        Lexer.create("name:", "SquidConf", "module:", "pygments.lexers.text", "class:", "SquidConfLexer");
        Lexer.create("name:", "Stan", "module:", "pygments.lexers.math", "class:", "StanLexer");
        Lexer.create("name:", "Standard ML", "module:", "pygments.lexers.functional", "class:", "SMLLexer");
        Lexer.create("name:", "Tcl", "module:", "pygments.lexers.agile", "class:", "TclLexer");
        Lexer.create("name:", "Tcsh", "module:", "pygments.lexers.shell", "class:", "TcshLexer");
        Lexer.create("name:", "TeX", "module:", "pygments.lexers.text", "class:", "TexLexer");
        Lexer.create("name:", "Tea", "module:", "pygments.lexers.templates", "class:", "TeaTemplateLexer");
        Lexer.create("name:", "Text only", "module:", "pygments.lexers.special", "class:", "TextLexer");
        Lexer.create("name:", "Treetop", "module:", "pygments.lexers.parsers", "class:", "TreetopLexer");
        Lexer.create("name:", "TypeScript", "module:", "pygments.lexers.web", "class:", "TypeScriptLexer");
        Lexer.create("name:", "UrbiScript", "module:", "pygments.lexers.other", "class:", "UrbiscriptLexer");
        Lexer.create("name:", "VB.net", "module:", "pygments.lexers.dotnet", "class:", "VbNetLexer");
        Lexer.create("name:", "VGL", "module:", "pygments.lexers.other", "class:", "VGLLexer");
        Lexer.create("name:", "Vala", "module:", "pygments.lexers.compiled", "class:", "ValaLexer");
        Lexer.create("name:", "Velocity", "module:", "pygments.lexers.templates", "class:", "VelocityLexer");
        Lexer.create("name:", "VimL", "module:", "pygments.lexers.text", "class:", "VimLexer");
        Lexer.create("name:", "XML", "module:", "pygments.lexers.web", "class:", "XmlLexer");
        Lexer.create("name:", "XML+Cheetah", "module:", "pygments.lexers.templates", "class:", "CheetahXmlLexer");
        Lexer.create("name:", "XML+Django/Jinja", "module:", "pygments.lexers.templates", "class:", "XmlDjangoLexer");
        Lexer.create("name:", "XML+Evoque", "module:", "pygments.lexers.templates", "class:", "EvoqueXmlLexer");
        Lexer.create("name:", "XML+Lasso", "module:", "pygments.lexers.templates", "class:", "LassoXmlLexer");
        Lexer.create("name:", "XML+Mako", "module:", "pygments.lexers.templates", "class:", "MakoXmlLexer");
        Lexer.create("name:", "XML+Myghty", "module:", "pygments.lexers.templates", "class:", "MyghtyXmlLexer");
        Lexer.create("name:", "XML+PHP", "module:", "pygments.lexers.templates", "class:", "XmlPhpLexer");
        Lexer.create("name:", "XML+Ruby", "module:", "pygments.lexers.templates", "class:", "XmlErbLexer");
        Lexer.create("name:", "XML+Smarty", "module:", "pygments.lexers.templates", "class:", "XmlSmartyLexer");
        Lexer.create("name:", "XML+Velocity", "module:", "pygments.lexers.templates", "class:", "VelocityXmlLexer");
        Lexer.create("name:", "XQuery", "module:", "pygments.lexers.web", "class:", "XQueryLexer");
        Lexer.create("name:", "XSLT", "module:", "pygments.lexers.web", "class:", "XsltLexer");
        Lexer.create("name:", "Xtend", "module:", "pygments.lexers.jvm", "class:", "XtendLexer");
        Lexer.create("name:", "YAML", "module:", "pygments.lexers.text", "class:", "YamlLexer");
        Lexer.create("name:", "aspx-cs", "module:", "pygments.lexers.dotnet", "class:", "CSharpAspxLexer");
        Lexer.create("name:", "aspx-vb", "module:", "pygments.lexers.dotnet", "class:", "VbNetAspxLexer");
        Lexer.create("name:", "autohotkey", "module:", "pygments.lexers.other", "class:", "AutohotkeyLexer");
        Lexer.create("name:", "c-objdump", "module:", "pygments.lexers.asm", "class:", "CObjdumpLexer");
        Lexer.create("name:", "ca65", "module:", "pygments.lexers.asm", "class:", "Ca65Lexer");
        Lexer.create("name:", "cfstatement", "module:", "pygments.lexers.templates", "class:", "ColdfusionLexer");
        Lexer.create("name:", "cpp-objdump", "module:", "pygments.lexers.asm", "class:", "CppObjdumpLexer");
        Lexer.create("name:", "d-objdump", "module:", "pygments.lexers.asm", "class:", "DObjdumpLexer");
        Lexer.create("name:", "dg", "module:", "pygments.lexers.agile", "class:", "DgLexer");
        Lexer.create("name:", "eC", "module:", "pygments.lexers.compiled", "class:", "ECLexer");
        Lexer.create("name:", "haXe", "module:", "pygments.lexers.web", "class:", "HaxeLexer");
        Lexer.create("name:", "objdump", "module:", "pygments.lexers.asm", "class:", "ObjdumpLexer");
        Lexer.create("name:", "reStructuredText", "module:", "pygments.lexers.text", "class:", "RstLexer");
        Lexer.create("name:", "reg", "module:", "pygments.lexers.text", "class:", "RegeditLexer");
        Lexer.create("name:", "sqlite3con", "module:", "pygments.lexers.sql", "class:", "SqliteConsoleLexer");
        Lexer.create("name:", "systemverilog", "module:", "pygments.lexers.hdl", "class:", "SystemVerilogLexer");
        Lexer.create("name:", "verilog", "module:", "pygments.lexers.hdl", "class:", "VerilogLexer");
        Lexer.create("name:", "vhdl", "module:", "pygments.lexers.hdl", "class:", "VhdlLexer");
      }
    });
  }

  private void createSolutionTable(final DB dbo) {
    dbo.createTable("solutions", "issue_id integer", "title text");
  }

  private void createSnippetTable(final DB dbo) {
    dbo.createTable("snippets", "user_id integer",
                                "solution_id integer",
                                "lexer_id integer",
                                "code text",
                                "comment text");
  }

  private void createTagTable(final DB dbo) {
    dbo.createTable("tags", "solution_id integer", "tool_id integer");
  }

  private void createTables(DB dbo) {
    Set<String> names = new HashSet<>();
    for (String table : dbo.getTableNames()) {
      names.add(table.toLowerCase());
    }

    if (!names.contains("users")) {
      createUserTable(dbo);
    }
    if (!names.contains("issues")) {
      createIssueTable(dbo);
    }
    if (!names.contains("tools")) {
      createToolTable(dbo);
    }
    if (!names.contains("lexers")) {
      createLexerTable(dbo);
    }
    if (!names.contains("solutions")) {
      createSolutionTable(dbo);
    }
    if (!names.contains("snippets")) {
      createSnippetTable(dbo);
    }
    if (!names.contains("tags")) {
      createTagTable(dbo);
    }
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    DB dbo = getDatabase();
    createTables(dbo);
    context.setAttribute("dbo", dbo);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    DB dbo = (DB) context.getAttribute("dbo");
    if (dbo != null) {
      dbo.close();
    }
  }
}
