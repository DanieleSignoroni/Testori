package com.thera.thermfw.web;

import java.util.*;
import javax.servlet.jsp.*;
import com.thera.thermfw.ad.*;
import com.thera.thermfw.base.*;

/*
 * Revisions:
 * Number    Date          Owner   Descrizione
 * 01645     15/03/2004    DB      Allineato comportamento di "keyRel" alla editgrid
 * 02866     19/11/2004    AC      In caso di replacement per il reperimento di valori presenti in un TFML di un descrittore di classe si ricerca con il descrittore originale, e non con quello rimpiazzato
 * 04969     13/02/2006    MM      Aggiunto reload del nodo padre
 * 05908     22/09/2006    CB      Modifiche legate ai problemi avuti quando c'è un replacement del classHDR
 * 43479     30/09/2024    YA      Icone svg negli alberi
 */

public class WebNodeProperties
{
   protected String classHdr;
   protected String classAD;
   protected String jsp;
   protected String image;
   public int numOfSon = 0;
   protected boolean groupSonCD = false;
   protected String[] sonCDStrings = null;
   protected String sonCDs;
   protected String keyRel;
   protected String partialKeyRel;
   protected int propertyNumber = 0;
   protected String[] actionsReloadMenu = null;
   protected String[] actionsReloadNode = null;
   protected String[] actionsReloadTree = null;
//Fix 04969 MM
   protected String[] actionsReloadParentNode = null;
   //Mod.1497
   protected String defaultAction = null;
   protected String[] defaultActionParams = null;

// Fix 2866 AC inizio

   private String getReplacingClassADCollectionName(String classADCollectionName)
   {
     try
     {
       //return ClassADCollectionManager.collectionWithName(classADCollectionName).getClassName();  //5908
       return ClassADCollectionManager.collectionWithName(classADCollectionName).getOriginalClassAdCollectionName(); //5908
     }
     catch(NoSuchFieldException e)
     {
       return classADCollectionName;
     }
     catch(NoSuchElementException e)
     {
       return classADCollectionName;
     }
   }

// Fix 2866 AC fine

   public WebNodeProperties(String allInfo)
   {
      StringTokenizer st = new StringTokenizer(allInfo, "$$$");
//      this.classHdr = st.nextToken();   // Fix 2866 AC
      this.classHdr = getReplacingClassADCollectionName(st.nextToken());   // Fix 2866 AC
      this.classAD = st.nextToken();

      String jspValue = st.nextToken();
      if(!jspValue.equals("null"))
         this.jsp = jspValue;

      String imageValue = st.nextToken();
      if(!imageValue.equals("null"))
         this.image = imageValue;

      String groupSonCDValue = st.nextToken();
      if(groupSonCDValue.equals("true"))
         this.groupSonCD = true;

      String sonCDStringValue = st.nextToken();
      if(!sonCDStringValue.equals("null"))
         this.sonCDs = sonCDStringValue;
      extractSonCDs(sonCDs);

      String keyRelValue = st.nextToken();
      if(!keyRelValue.equals("null"))
         this.keyRel = keyRelValue;

      String partialKeyRelValue = st.nextToken();
      if(!partialKeyRelValue.equals("null"))
         this.partialKeyRel =  "'" + partialKeyRelValue + "'";;

      String propertyNumberValue = st.nextToken();
      this.propertyNumber = Integer.parseInt(propertyNumberValue);
   }


   public WebNodeProperties(String classHdr, String classAD,
                            String jsp, String image, boolean groupSonCD,
                            String sonCDString, String keyRel)
   {
     //      this.classHdr = classHdr;   // Fix 2866 AC
      this.classHdr = getReplacingClassADCollectionName(classHdr);   // Fix 2866 AC
      this.classAD = classAD;
      this.jsp = jsp;
      this.image = image;
      this.groupSonCD = groupSonCD;
      this.sonCDs = sonCDString;
      extractSonCDs(sonCDs);
      this.keyRel = keyRel;
   }
//Fix 04969 MM
   public WebNodeProperties(String classHdr, String classAD,
                            String jsp, String image, boolean groupSonCD,
                            String sonCDString, String keyRel,
                            String[] actionsReloadMenu, String[] actionsReloadNode, String[] actionsReloadTree,
                            String[] actionsReloadParentNode)
   {
     this(classHdr, classAD, jsp, image, groupSonCD, sonCDString, keyRel,
         actionsReloadMenu, actionsReloadNode, actionsReloadTree);
     this.actionsReloadParentNode = actionsReloadParentNode;
   }

   public WebNodeProperties(String classHdr, String classAD,
       String jsp, String image, boolean groupSonCD,
       String sonCDString, String keyRel,
       String[] actionsReloadMenu, String[] actionsReloadNode, String[] actionsReloadTree)
   {
     //      this.classHdr = classHdr;   // Fix 2866 AC
     this.classHdr = getReplacingClassADCollectionName(classHdr);   // Fix 2866 AC
     this.classAD = classAD;
     this.jsp = jsp;
     this.image = image;
     this.groupSonCD = groupSonCD;
     this.sonCDs = sonCDString;
     extractSonCDs(sonCDs);
     this.keyRel = keyRel;
     this.actionsReloadMenu = actionsReloadMenu;
     this.actionsReloadNode = actionsReloadNode;
     this.actionsReloadTree = actionsReloadTree;
   }

   //Mod.1497
   public WebNodeProperties(String classHdr, String classAD,
                            String jsp, String image, boolean groupSonCD,
                            String sonCDString, String keyRel, String defaultAction)
   {
     //      this.classHdr = classHdr;   // Fix 2866 AC
      this.classHdr = getReplacingClassADCollectionName(classHdr);   // Fix 2866 AC
      this.classAD = classAD;
      this.jsp = jsp;
      this.image = image;
      this.groupSonCD = groupSonCD;
      this.sonCDs = sonCDString;
      extractSonCDs(sonCDs);
      this.keyRel = keyRel;
      this.defaultAction = defaultAction;
   }

   public WebNodeProperties(String classHdr, String classAD,
                            String jsp, String image, boolean groupSonCD,
                            String sonCDString, String keyRel,
                            String[] actionsReloadMenu, String[] actionsReloadNode, String[] actionsReloadTree,
                            //Fix 04969 MM
                            String[] actionsReloadParentNode,
                            String defaultAction)
   {
//      this.classHdr = classHdr;   // Fix 2866 AC
      this.classHdr = getReplacingClassADCollectionName(classHdr);   // Fix 2866 AC
      this.classAD = classAD;
      this.jsp = jsp;
      this.image = image;
      this.groupSonCD = groupSonCD;
      this.sonCDs = sonCDString;
      extractSonCDs(sonCDs);
      this.keyRel = keyRel;
      this.actionsReloadMenu = actionsReloadMenu;
      this.actionsReloadNode = actionsReloadNode;
      this.actionsReloadTree = actionsReloadTree;
      //Fix 04969 MM
      this.actionsReloadParentNode = actionsReloadParentNode;
      this.defaultAction = defaultAction;
   }

   public void setSonCDs(String sonCDs)
   {
      this.sonCDs = sonCDs;
      extractSonCDs(sonCDs);
   }

   protected void extractSonCDs(String sonCDsValue)
   {
      if(sonCDsValue != null)
      {
         StringTokenizer st = new StringTokenizer(sonCDsValue, ",");
         this.numOfSon = st.countTokens();
         this.sonCDStrings = new String[numOfSon];
         int i = 0;
         while(st.hasMoreTokens())
         {
            this.sonCDStrings[i] = st.nextToken().trim();
            i++;
         }
      }
   }

   public void setActionsReloadMenu(String actions)
   {
      actionsReloadMenu = extractActions(actions);
   }

   public void setActionsReloadNode(String actions)
   {
      actionsReloadNode = extractActions(actions);
   }

   public void setActionsReloadTree(String actions)
   {
      actionsReloadTree = extractActions(actions);
   }

//Fix 04969 MM
   public void setActionsReloadParentNode(String actions)
   {
      actionsReloadParentNode = extractActions(actions);
   }

   protected String[] extractActions(String actions)
   {
      String[] resultActions = null;
      if(actions != null && actions.length() > 0)
      {
         StringTokenizer st = new StringTokenizer(actions, ",");
         resultActions = new String[st.countTokens()];
         int i = 0;
         while(st.hasMoreTokens())
         {
            resultActions[i] = st.nextToken().trim();
            i++;
         }
      }
      return resultActions;
   }

   public boolean isReloadingActionsPresent()
   {
//Fix 04969 MM
     //return actionsReloadMenu != null || actionsReloadNode != null || actionsReloadTree != null;
     return actionsReloadMenu != null || actionsReloadNode != null || actionsReloadTree != null || actionsReloadParentNode != null;
   }

   //Mod.1947
   public void setDefaultAction(String defaultAction)
   {
      this.defaultAction = defaultAction;
   }
   public void setDefaultActionParams(String params)
   {
      this.defaultActionParams = extractActions(params);
   }

   public String getConstructor()
   {
      String sCDs = getQuotedString(sonCDs);
      if(sonCDs == null)
         sCDs = "(String)null";

      String constr = "new WebNodeProperties(" + getQuotedString(classHdr) + ", " + getQuotedString(classAD) + ", " +
                      getQuotedString(jsp) + ", " + getQuotedString(image) + ", " +
                      groupSonCD + ", " + sCDs  + ", " + getQuotedString(keyRel);
      //Mod.1947
      if (defaultAction != null)
        constr = constr + ", \"" + defaultAction + "\""; //Mod.1813
      constr = constr + ")";
      return constr;
   }

   public String getConstructorForActions()
   {
      String sCDs = getQuotedString(sonCDs);
      if(sonCDs == null)
         sCDs = "(String)null";

      String actionsMenu = getActionsAsPAram(actionsReloadMenu);
      String actionsNode = getActionsAsPAram(actionsReloadNode);
      String actionsTree = getActionsAsPAram(actionsReloadTree);

      //Fix 04969 MM
      String actionsParentNode = getActionsAsPAram(actionsReloadParentNode);

      String constr = "new WebNodeProperties(" + getQuotedString(classHdr) + ", " + getQuotedString(classAD) + ", " +
                      getQuotedString(jsp) + ", " + getQuotedString(image) + ", " +
                      groupSonCD + ", " + sCDs  + ", " + getQuotedString(keyRel)  + ", " +
                      //Fix 04969 MM
                      //actionsMenu + ", " + actionsNode + ", " + actionsTree;
                      actionsMenu + ", " + actionsNode + ", " + actionsTree+ ", " + actionsParentNode;
      //Mod.1947
      if (defaultAction != null)
        constr = constr + ", \"" + defaultAction + "\"";
      constr = constr + ")";
      return constr;
   }

   public String getQuotedString(String str)
   {
      if(str == null)
         return "null";
      return "\"" + str + "\"";
   }

   protected String getActionsAsPAram(String[] actions)
   {
      String param = "null";
      if(actions != null)
      {
         param = "new String[]{";
         for(int i = 0; i < actions.length; i++)
         {
            if(i > 0)
               param += ",";
            param += getQuotedString(actions[i]);
         }
         param +="}";
      }
      return param;
   }

   public String getClassHdr()
   {
      return classHdr;
   }

   public ClassADCollection getClassHdrObject()
   {
      ClassADCollection cad = null;
      try
      {
         cad = ClassADCollectionManager.collectionWithName(classHdr);
      }
      catch(Exception e)
      {
         e.printStackTrace(Trace.excStream);
      }
      return cad;
   }

   public String getClassAD()
   {
      return classAD;
   }

   public String getImage()
   {
      return image;
   }

   public String getJSP()
   {
      return jsp;
   }

   public boolean isGroupSonCD()
   {
      return groupSonCD;
   }

   public String[] getSonCDStrings()
   {
      return sonCDStrings;
   }

   public String getKeyRel()
   {
      return keyRel;
   }

   public String getPartialKeyRel()
   {
      if(partialKeyRel == null)
      {
         partialKeyRel = "";
         if(keyRel == null || keyRel.length() == 0)
         {
            try
            {
               ClassADCollection currentCADC = ClassADCollectionManager.collectionWithName(classHdr);
               Vector keys = currentCADC.getKeys();
               for(int i = 0; i < keys.size() - 1; i++)
               {
                  String curKeyName = ((ClassAD)keys.elementAt(i)).getAttributeName();
                  if(partialKeyRel.length() > 0)
                     partialKeyRel += ",";
                  partialKeyRel += curKeyName;
               }
            }
            catch(Exception ex)
            {
               ex.printStackTrace();
            }
         }
         else
         {
            StringTokenizer keyTok = new StringTokenizer(keyRel, ";");
            while(keyTok.hasMoreTokens())
            {
               String curKeyRel = keyTok.nextToken();
               StringTokenizer curKeyTok = new StringTokenizer(curKeyRel, ",");
//Fix 01645 MM
//Prendo il primo anziché il secondo token
//               curKeyTok.nextToken();
               if(partialKeyRel.length() > 0)
                  partialKeyRel += ",";
               partialKeyRel += curKeyTok.nextToken().trim();
            }
         }
         partialKeyRel = "'" + partialKeyRel + "'";
      }
      return partialKeyRel;
   }

   public int getPropertyNumber()
   {
      return propertyNumber;
   }

   public void write(String treeElementName, JspWriter out) throws java.io.IOException
   {
      String constr = "new NodeProperties(" + getQuotedString(classHdr) + "," +
                                              getQuotedString(classAD)  + "," +
                                              getPartialKeyRel()  + "," +
                                              getQuotedString(jsp) + "," +
                                              //Fix 43479 - inizio
                                              //getQuotedString(image) + "," +
                                              getQuotedString(StyleDefinition.getInstance().getImgPath(image)) + "," +
                                              //Fix 43479 - fine
                                              propertyNumber + "," +
                                              getActionsAsParamOfWrite(actionsReloadMenu) + "," +
                                              getActionsAsParamOfWrite(actionsReloadNode) + "," +
                                              getActionsAsParamOfWrite(actionsReloadTree) + "," +
                                              //Fix 04969 MM
                                              getActionsAsParamOfWrite(actionsReloadParentNode) + "," +
                                              getActionsAsParamOfWrite(defaultActionParams) +//Mod.1497
                                          ")";
      out.println(treeElementName + "[" + getQuotedString(classHdr) + "] = " + constr + ";");
      out.println(treeElementName + "ByNumber[" + propertyNumber + "] = " + treeElementName + "[" + getQuotedString(classHdr) + "];");
   }

   protected String getActionsAsParamOfWrite(String[] actions)
   {
      String param = "null";
      if(actions != null)
      {
         param = "new Array(";
         for(int i = 0; i < actions.length; i++)
         {
            if(i > 0)
               param += ",";
            param += getQuotedString(actions[i]);
         }
         param +=")";
      }
      return param;
   }


   public String getStringForSubmit()
   {
      String tempPartialKeyRel = partialKeyRel.substring(1, partialKeyRel.length() -1);
      String result = classHdr + "$$$" +
                      classAD + "$$$" +
                      (jsp != null ? jsp : "null") + "$$$" +
                      (image != null ? image : "null") + "$$$" +
                      (groupSonCD ? "true" : "false") + "$$$" +
                      (sonCDs != null ? sonCDs : "null") + "$$$" +
                      (keyRel != null ? keyRel : "null") + "$$$" +
                      (tempPartialKeyRel.length() > 0 ? tempPartialKeyRel : "null") + "$$$" +
                      propertyNumber;
      return result;
   }

}
