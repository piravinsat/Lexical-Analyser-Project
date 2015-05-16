/*******************************************************************************
*
* Header file generated by RDP on Oct 07 2013 19:00:20 from expr1.bnf
*
*******************************************************************************/
#ifndef EXPR1_H
#define EXPR1_H

#include "arg.h"
#include "graph.h"
#include "hist.h"
#include "memalloc.h"
#include "scan.h"
#include "set.h"
#include "symbol.h"
#include "textio.h"


/* Maximum number of passes */
#define RDP_PASSES 1

/* Time and date stamp */
#define RDP_STAMP "Generated on Oct 07 2013 19:00:20 and compiled on " __DATE__ " at " __TIME__ 

/* Token enumeration */
enum
{
RDP_TT_BOTTOM = SCAN_P_TOP,
RDP_T_42 /* * */ = SCAN_P_TOP,RDP_T_43 /* + */,RDP_T_a,RDP_T_b,
RDP_TT_TOP
};

/* Tree data types */

typedef struct rdp_tree_node_data_struct
{
  SCAN_DATA
  
} rdp_tree_node_data;
typedef struct rdp_tree_edge_data_struct
{
  int rdp_edge_kind;
  
} rdp_tree_edge_data;

/* Symbol table support */
/* Parser start production */
void S(void);



#endif

/* End of expr1.h */