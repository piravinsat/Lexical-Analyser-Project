/*******************************************************************************
*
* Parser generated by RDP on Dec 05 2013 05:17:08 from regex.bnf
*
*******************************************************************************/
#include <time.h>
#include "rdparser.h"

char
  *rdp_sourcefilename,          /* current source file name */
  **rdp_sourcefilenames,        /* array of source file names */
  *rdp_outputfilename = "regex.out";         /* output file name */

int
  rdp_symbol_echo = 0,                 /* symbol echo flag */
  rdp_verbose = 0,                     /* verbosity flag */
  rdp_sourcefilenumber,                /* Source file counter */
  rdp_pass;                            /* pass number */

int rdp_error_return = 0;              /* return value for main routine */

char *rdp_tokens = "IGNORE\0" 
"ID\0" "INTEGER\0" "REAL\0" "CHAR\0" "CHAR_ESC\0" "STRING\0" "STRING_ESC\0" "COMMENT\0" 
"COMMENT_VISIBLE\0" "COMMENT_NEST\0" "COMMENT_NEST_VISIBLE\0" "COMMENT_LINE\0" "COMMENT_LINE_VISIBLE\0" "EOF\0" "EOLN\0" "'#'\0" 
"'('\0" "')'\0" "'*'\0" "'|'\0" ;


/* Tree update function for noterminal nodes */
static int rdp_tree_update = 0;

rdp_tree_node_data* rdp_tree_last_child;

rdp_tree_node_data* rdp_add_node(char* id, rdp_tree_node_data* rdp_tree)
{
  if (rdp_tree_update)
  {
     rdp_tree_node_data *node  = (rdp_tree_node_data*) graph_insert_node(sizeof(rdp_tree_node_data), rdp_tree);
     if (id != NULL)
       node->id = id;
     else
       memcpy(node, text_scan_data, sizeof(scan_data));
       return node;
  }
  else
    return NULL;
}

rdp_tree_node_data* rdp_add_child(char* id, rdp_tree_node_data* rdp_tree)
{
  if (rdp_tree_update)
  {
    rdp_tree_last_child = (rdp_tree_node_data*) graph_insert_node(sizeof(rdp_tree_node_data), rdp_tree);
      if (id != NULL)
        rdp_tree_last_child->id = id;
    else
      memcpy(rdp_tree_last_child, text_scan_data, sizeof(scan_data));

    ((rdp_tree_edge_data*) graph_insert_edge_after_final(sizeof(rdp_tree_edge_data), rdp_tree_last_child, rdp_tree))->rdp_edge_kind = 1;
    return rdp_tree_last_child;
  }
  else
    return NULL;
}

rdp_tree_node_data* rdp_add_parent(char* id, rdp_tree_node_data* rdp_tree)
{
  if (rdp_tree_update)
  {
    rdp_tree_node_data *parent = (rdp_tree_node_data*) graph_insert_node_parent(sizeof(rdp_tree_node_data), sizeof(rdp_tree_edge_data), rdp_tree);
    if (id != NULL)
      parent->id = id;
    else
      memcpy(parent, text_scan_data, sizeof(scan_data));

    ((rdp_tree_edge_data*) graph_next_out_edge(parent))->rdp_edge_kind = 1;

    return parent;
  }
  else
    return NULL;
}


/* Load keywords */
static void rdp_load_keywords(void)
{
  scan_load_keyword("#", NULL, RDP_T_35 /* # */, SCAN_P_IGNORE);
  scan_load_keyword("(", NULL, RDP_T_40 /* ( */, SCAN_P_IGNORE);
  scan_load_keyword(")", NULL, RDP_T_41 /* ) */, SCAN_P_IGNORE);
  scan_load_keyword("*", NULL, RDP_T_42 /* * */, SCAN_P_IGNORE);
  scan_load_keyword("|", NULL, RDP_T_124 /* | */, SCAN_P_IGNORE);
}

/* Set declarations */

  set_ alt_first = SET_NULL;
  set_ alt_stop = SET_NULL;
  set_ con_first = SET_NULL;
  set_ con_stop = SET_NULL;
  set_ kle_first = SET_NULL;
  set_ kle_stop = SET_NULL;
  set_ rdp_alt_2_first = SET_NULL;
  set_ rdp_con_0_first = SET_NULL;
  set_ rdp_con_1_first = SET_NULL;
  set_ rdp_con_2_first = SET_NULL;
  set_ rdp_kle_2_first = SET_NULL;
  set_ reg_first = SET_NULL;
  set_ reg_stop = SET_NULL;

/* Initialise sets */

static void rdp_set_initialise(void)
{
  set_assign_list(&alt_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&alt_stop, SCAN_P_EOF, RDP_T_41 /* ) */,SET_END);
  set_assign_list(&con_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&con_stop, SCAN_P_EOF, RDP_T_41 /* ) */, RDP_T_124 /* | */,SET_END);
  set_assign_list(&kle_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&kle_stop, SCAN_P_ID, SCAN_P_EOF, RDP_T_35 /* # */, RDP_T_40 /* ( */, RDP_T_41 /* ) */, RDP_T_124 /* | */,SET_END);
  set_assign_list(&rdp_alt_2_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&rdp_con_0_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&rdp_con_1_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&rdp_con_2_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&rdp_kle_2_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&reg_first, SCAN_P_ID, RDP_T_35 /* # */, RDP_T_40 /* ( */, SET_END);
  set_assign_list(&reg_stop, SCAN_P_ID, SCAN_P_EOF, RDP_T_35 /* # */, RDP_T_40 /* ( */, RDP_T_41 /* ) */, RDP_T_42 /* * */, 
RDP_T_124 /* | */,SET_END);
}

/* Parser forward declarations and macros */
void alt(rdp_tree_node_data* rdp_tree);
static void con(rdp_tree_node_data* rdp_tree);
static void kle(rdp_tree_node_data* rdp_tree);
static void reg(rdp_tree_node_data* rdp_tree);

/* Global directive code */
 void printTreeRec(rdp_tree_node_data *node, int level) {
        for (int i = 0; i < level; i++) text_printf(" ");
        text_printf("%s\n", node->id);
        for (void *edge = graph_next_out_edge(node); edge != NULL;
                                        edge = graph_next_out_edge(edge))
                printTreeRec((rdp_tree_node_data*) graph_destination(edge), level+1);
} 

/* Parser functions */
void alt(rdp_tree_node_data* rdp_tree)
{
  {
    con(rdp_add_child("con", rdp_tree));
    if (scan_test(NULL, RDP_T_124 /* | */, NULL))
    { /* Start of rdp_alt_1 */
      while (1)
      {
        {
          if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
          scan_test(NULL, RDP_T_124 /* | */, &alt_stop);
          scan_();
          con(rdp_add_child("con", rdp_tree));
          }
        break;   /* hi limit is 1! */
      }
    } /* end of rdp_alt_1 */
    else
    {
      /* default action processing for rdp_alt_1*/
      if (rdp_tree_update) {rdp_tree_node_data *temp = rdp_add_child(NULL, rdp_tree); temp->id = NULL; temp->token = SCAN_P_ID;}
    }
    scan_test_set(NULL, &alt_stop, &alt_stop);
   }
}

static void con(rdp_tree_node_data* rdp_tree)
{
  {
    kle(rdp_add_child("kle", rdp_tree));
    if (scan_test_set(NULL, &rdp_con_1_first, NULL))
    { /* Start of rdp_con_1 */
      while (1)
      {
        {
          kle(rdp_add_child("kle", rdp_tree));
          }
        break;   /* hi limit is 1! */
      }
    } /* end of rdp_con_1 */
    else
    {
      /* default action processing for rdp_con_1*/
      if (rdp_tree_update) {rdp_tree_node_data *temp = rdp_add_child(NULL, rdp_tree); temp->id = NULL; temp->token = SCAN_P_ID;}
    }
    scan_test_set(NULL, &con_stop, &con_stop);
   }
}

static void kle(rdp_tree_node_data* rdp_tree)
{
  {
    reg(rdp_add_child("reg", rdp_tree));
    if (scan_test(NULL, RDP_T_42 /* * */, NULL))
    { /* Start of rdp_kle_1 */
      while (1)
      {
        {
          if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
          scan_test(NULL, RDP_T_42 /* * */, &kle_stop);
          scan_();
          }
        break;   /* hi limit is 1! */
      }
    } /* end of rdp_kle_1 */
    else
    {
      /* default action processing for rdp_kle_1*/
      if (rdp_tree_update) {rdp_tree_node_data *temp = rdp_add_child(NULL, rdp_tree); temp->id = NULL; temp->token = SCAN_P_ID;}
    }
    scan_test_set(NULL, &kle_stop, &kle_stop);
   }
}

static void reg(rdp_tree_node_data* rdp_tree)
{
  {
    if (scan_test(NULL, SCAN_P_ID, NULL))
    {
      if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
      scan_test(NULL, SCAN_P_ID, &reg_stop);
      scan_();
    }
    else
    if (scan_test(NULL, RDP_T_35 /* # */, NULL))
    {
      if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
      scan_test(NULL, RDP_T_35 /* # */, &reg_stop);
      scan_();
    }
    else
    if (scan_test(NULL, RDP_T_40 /* ( */, NULL))
    {
      if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
      scan_test(NULL, RDP_T_40 /* ( */, &reg_stop);
      scan_();
      alt(rdp_add_child("alt", rdp_tree));
      if (rdp_tree_update) rdp_add_child(NULL, rdp_tree);
      scan_test(NULL, RDP_T_41 /* ) */, &reg_stop);
      scan_();
    }
    else
      scan_test_set(NULL, &reg_first, &reg_stop)    ;
    scan_test_set(NULL, &reg_stop, &reg_stop);
   }
}

int main(int argc, char *argv[])
{
  clock_t rdp_finish_time, rdp_start_time = clock();
  int
    rdp_symbol_statistics = 0,    /* show symbol_ table statistics flag */
    rdp_line_echo_all = 0,        /* make a listing on all passes flag */
    rdp_filter = 0,               /* filter flag */
    rdp_line_echo = 0,            /* make listing flag */

    rdp_lexicalise = 0;            /* print lexicalised output flag */

  unsigned long rdp_textsize = 35000l;   /* size of scanner text array */

  unsigned long rdp_tabwidth = 8l;   /* tab expansion width */

  char* rdp_vcg_filename = NULL;      /* filename for -V option */

  rdp_tree_node_data* rdp_tree = (rdp_tree_node_data*) graph_insert_graph("RDP derivation tree");  /* hook for derivation tree */
  rdp_tree_node_data* rdp_tree_root;

  arg_message("rdparser\n" RDP_STAMP "\n\n""Usage: rdparser [options] source");

  arg_message("");
  arg_boolean('f', "Filter mode (read from stdin and write to stdout)", &rdp_filter);
  arg_boolean('l', "Make a listing", &rdp_line_echo);
  arg_boolean('L', "Print lexicalised source file", &rdp_lexicalise);
  arg_string ('o', "Write output to filename", &rdp_outputfilename);
  arg_boolean('s', "Echo each scanner symbol as it is read", &rdp_symbol_echo);
  arg_boolean('S', "Print summary symbol table statistics", &rdp_symbol_statistics);
  arg_numeric('t', "Tab expansion width (default 8)", &rdp_tabwidth);
  arg_numeric('T', "Text buffer size in bytes for scanner (default 20000)", &rdp_textsize);
  arg_boolean('v', "Set verbose mode", &rdp_verbose);
  arg_string ('V', "Write derivation tree to filename in VCG format", &rdp_vcg_filename);

  rdp_sourcefilenames = arg_process(argc, argv);

  /* Fix up filetypes */
  for (rdp_sourcefilenumber = 0; rdp_sourcefilenames[rdp_sourcefilenumber] != NULL; rdp_sourcefilenumber++)
    rdp_sourcefilenames[rdp_sourcefilenumber] = text_default_filetype(rdp_sourcefilenames[rdp_sourcefilenumber], "");

  if (rdp_filter)
  {
    rdp_sourcefilenames[0] = "-";
    rdp_outputfilename = "-";
    rdp_sourcefilenames[1] = NULL;     /* make sure no further filenames are taken from the array */

  }
  if ((rdp_sourcefilename = rdp_sourcefilenames[0]) == NULL)
     arg_help("no source files specified");

  if (rdp_sourcefilenames[1] != NULL)
    text_message(TEXT_FATAL, "multiple source files not allowed\n");
  text_init(rdp_textsize, 25, 100, (int) rdp_tabwidth);
  scan_init(0, 0, 0, rdp_symbol_echo, rdp_tokens);
  if (rdp_lexicalise)
    scan_lexicalise();
  rdp_set_initialise();
  rdp_load_keywords();
  if (rdp_verbose)
     text_printf("\nrdparser\n" RDP_STAMP "\n\n");
  for (rdp_pass = 1; rdp_pass <= RDP_PASSES; rdp_pass++)
  {
    rdp_tree_update = rdp_pass == RDP_PASSES;
    text_echo(rdp_line_echo_all || (rdp_line_echo && rdp_pass == RDP_PASSES));

    for (rdp_sourcefilenumber = 0; (rdp_sourcefilename = rdp_sourcefilenames[rdp_sourcefilenumber]) != NULL; rdp_sourcefilenumber++)
    {
      if (text_open(rdp_sourcefilename) == NULL)
        arg_help("unable to open source file");

      text_get_char();
      scan_();

      alt(rdp_tree_root = rdp_add_node("alt", rdp_tree));            /* call parser at top level */
      if (text_total_errors() != 0)
        text_message(TEXT_FATAL, "error%s detected in source file ''\n", text_total_errors() == 1 ? "" : "s", rdp_sourcefilename);   /* crash quietly */ 
      graph_epsilon_prune_rdp_tree(rdp_tree_root, sizeof(rdp_tree_edge_data));
    }
  }

  rdp_sourcefilename = rdp_sourcefilenames[0];     /* Reset filename to first file in the list */

  graph_set_root(rdp_tree, rdp_tree_root);
  if (rdp_vcg_filename != NULL)
  {
    FILE *rdp_vcg_file;

    if (*rdp_vcg_filename == '\0')   /* No filename supplied */
      rdp_vcg_filename = "rdparser";
    rdp_vcg_file = fopen((rdp_vcg_filename = text_default_filetype(rdp_vcg_filename, "vcg")), "w");

    if (rdp_vcg_file == NULL)
      text_message(TEXT_FATAL, "unable to open VCG file '%s' for write\n", rdp_vcg_filename);

    if (rdp_verbose)
      text_message(TEXT_INFO, "Dumping derivation tree to VCG file '%s'\n", rdp_vcg_filename);

    text_redirect(rdp_vcg_file);
    graph_vcg(rdp_tree, NULL, scan_vcg_print_node, scan_vcg_print_edge);
    text_redirect(stdout);
    fclose(rdp_vcg_file);
  }

   printTreeRec((rdp_tree_node_data*) graph_root(rdp_tree), 0); 
  if (rdp_symbol_statistics)
  {
    symbol_print_all_table_statistics(11);
    symbol_print_all_table();

  }
  text_print_total_errors();
  if (rdp_verbose)
  {
    rdp_finish_time = clock();
    text_message(TEXT_INFO, "%.3f CPU seconds used\n", ((double) (rdp_finish_time-rdp_start_time)) / CLOCKS_PER_SEC);
  }
  return rdp_error_return;
}

/* End of rdparser.c */
