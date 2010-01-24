" Vim syntax file
" Language   : Scala (http://scala-lang.org/)
" Maintainers: Stefan Matthias Aust, Julien Wetterwald
" Last Change: 2007 June 13
" Revision   : $Id$
"        $URL$

if version < 600
  syntax clear
elseif exists("b:current_syntax")
  finish
endif

syn case match
syn sync minlines=50

" reserved words
syn keyword scalaKeyword abstract case catch class def do else extends false final finally for forSome if implicit import lazy match new null object override package private protected requires return sealed super this throw trait try true type val var while with yield

syn match scalaAnnotation "@[a-zA-Z0-9]\+"

" standard types
syn keyword scalaStandartType Any AnyRef AnyVal Unit Boolean Char Byte short Int Long Float Double ScalaObject String Null Nothing

syn region scalaBracket start="\[" end="\]" contains=scalaBracket
syn region scalaEscapeReserved start="`" end="`"

" comments
syn match scalaLineComment "//.*" contains=scalaTodo
syn region scalaComment start="/\*" end="\*/" contains=scalaComment
syn case ignore
syn include @scalaHtml syntax/html.vim
unlet b:current_syntax
syn case match
syn region scalaDocComment start="/\*\*" end="\*/" contains=scalaDocTags,@scalaHtml
syn region scalaDocTags start="{@\(link\|linkplain\|inherit[Dd]oc\|doc[rR]oot\|value\)" end="}" contained
syn match scalaDocTags "@[a-z]\+" contained

" multi-line string literals
syn region scalaMultiLineString start="\"\"\"" end="\"\"\"" contains=scalaUnicode
syn match scalaUnicode "\\u[0-9a-fA-F]\{4}" contained

" string literals with escapes
syn region scalaString start="\"" skip="\\\"" end="\"" contains=scalaStringEscape
syn match scalaStringEscape "\\u[0-9a-f][0-9a-f][0-9a-f][0-9a-f]" contained
syn match scalaStringEscape "\\[nrfvb\\\"]" contained

" symbol and character literals
syn match scalaSymbol "'[_a-zA-Z0-9][_a-zA-Z0-9]*\>"
syn match scalaChar "'[^'\\]'\|'\\.'\|'\\u[0-9a-fA-F]\{4}'"

" number literals
syn match scalaNumber "\<\(0[0-7]*\|0[xX]\x\+\|\d\+\)[lL]\=\>"
syn match scalaNumber "\(\<\d\+\.\d*\|\.\d\+\)\([eE][-+]\=\d\+\)\=[fFdD]\="
syn match scalaNumber "\<\d\+[eE][-+]\=\d\+[fFdD]\=\>"
syn match scalaNumber "\<\d\+\([eE][-+]\=\d\+\)\=[fFdD]\>"

" xml literals
syn match scalaXmlTag "<[a-zA-Z]\_[^>]*/>" contains=scalaXmlQuote,scalaXmlEscape,scalaXmlString
syn region scalaXmlString start="\"" end="\"" contained
syn match scalaXmlStart "<[a-zA-Z]\_[^>]*>" contained contains=scalaXmlQuote,scalaXmlEscape,scalaXmlString
syn region scalaXml start="<\([a-zA-Z]\_[^>]*\_[^/]\|[a-zA-Z]\)>" matchgroup=scalaXmlStart end="</\_[^>]\+>" contains=scalaXmlEscape,scalaXmlQuote,scalaXml,scalaXmlStart,scalaXmlComment
syn region scalaXmlEscape matchgroup=scalaXmlEscapeSpecial start="{" matchgroup=scalaXmlEscapeSpecial end="}" contained contains=TOP
syn match scalaXmlQuote "&[^;]\+;" contained
syn match scalaXmlComment "<!--\_[^>]*-->" contained

syn sync fromstart

" map Scala groups to standard groups
hi link scalaKeyword Keyword
hi link scalaAnnotation PreProc
hi link scalaNumber Number
hi link scalaString String
hi link scalaChar String
hi link scalaMultiLineString String
hi link scalaStringEscape Special
hi link scalaSymbol Special
hi link scalaUnicode Special
hi link scalaComment Comment
hi link scalaLineComment Comment
hi link scalaDocComment Comment
hi link scalaDocTags PreProc
hi link scalaXml String
hi link scalaXmlTag Include
hi link scalaXmlString String
hi link scalaXmlStart Include
hi link scalaXmlEscape Normal
hi link scalaXmlEscapeSpecial Special
hi link scalaXmlQuote Special
hi link scalaXmlComment Comment
hi link scalaStandartType Type
hi link scalaBracket Special

let b:current_syntax = "scala"
