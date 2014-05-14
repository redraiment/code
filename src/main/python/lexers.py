from pygments.lexers import get_all_lexers, get_lexer_by_name

for name, aliases, filetypes, mimetypes in get_all_lexers():
    print name
    # if aliases:
    #     alias = aliases[0]
    #     lexer = get_lexer_by_name(alias).__class__
    #     module_name = lexer.__module__
    #     class_name = lexer.__name__
    #     print "insert into lexers (name, module, class) values ('%s', '%s', '%s');" % (name, module_name, class_name)
