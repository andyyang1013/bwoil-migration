package com.bwoil.c2b.migration.core.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlUtil {

    /**
     * 获取表名
     *
     * @param sql sql语句
     * @return 表名
     */
    public static String getTable(String sql) {
        if (StringUtils.isBlank(sql)) {
            throw new RuntimeException("unsupported sql : " + sql);
        }
        try {
            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                return getFromClause(sql);
            }
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                return getInsertTable(sql);
            }
            if (sql.trim().toUpperCase().startsWith("UPDATE")) {
                return getUpdateTable(sql);
            }
        } catch (JSQLParserException e) {
            throw new RuntimeException("unsupported sql : " + sql, e);
        }
        return null;
    }

    /**
     * 获取sql结构
     *
     * @param sql sql语句
     * @return sql结构
     */
    public static SqlStructure getStructure(String sql) {
        if (StringUtils.isBlank(sql)) {
            throw new RuntimeException("unsupported sql : " + sql);
        }
        try {
            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                SqlStructure structure = new SqlStructure();
                structure.setType("SELECT");
                structure.setSelectClause(getSelectClause(sql));
                structure.setFromClause(getFromClause(sql));
                structure.setGroupClause(getGroupClause(sql));
                structure.setWhereClause(getWhereClause(sql));
                return structure;
            }
        } catch (JSQLParserException e) {
            throw new RuntimeException("unsupported sql : " + sql, e);
        }
        throw new RuntimeException("unsupported sql : " + sql);
    }

    private static Select parserSelect(String sql) throws JSQLParserException {
        return (Select) CCJSqlParserUtil.parse(sql);
    }

    private static Insert parserInsert(String sql) throws JSQLParserException {
        return (Insert) CCJSqlParserUtil.parse(sql);
    }

    private static Update parserUpdate(String sql) throws JSQLParserException {
        return (Update) CCJSqlParserUtil.parse(sql);
    }

    private static String getInsertTable(String sql) throws JSQLParserException {
        Insert insert = parserInsert(sql);
        return insert.getTable().getName();
    }

    private static String getUpdateTable(String sql) throws JSQLParserException {
        Update update = parserUpdate(sql);
        return update.getTable().getName();
    }

    private static String getSelectClause(String sql) throws JSQLParserException {
        final String[] selectClause = {null};
        Select select = parserSelect(sql);
        select.getSelectBody().accept(new SelectVisitor() {
            @Override
            public void visit(PlainSelect plainSelect) {
                List<SelectItem> selectItems = plainSelect.getSelectItems();
                String s = selectItems.toString();
                selectClause[0] = s.substring(1, s.length() - 1);
            }

            @Override
            public void visit(SetOperationList setOpList) {

            }

            @Override
            public void visit(WithItem withItem) {

            }
        });
        return selectClause[0];
    }

    private static String getFromClause(String sql) throws JSQLParserException {
        final String[] fromClause = new String[1];
        Select select = parserSelect(sql);
        select.getSelectBody().accept(new SelectVisitor() {
            @Override
            public void visit(PlainSelect plainSelect) {
                FromItem fromItem = plainSelect.getFromItem();
                fromClause[0] = fromItem.toString();

                List<Join> joins = plainSelect.getJoins();
                if (joins != null) {
                    for (Join join : joins) {
                        fromClause[0] += " " + join.toString();
                    }
                }
            }

            @Override
            public void visit(SetOperationList setOpList) {

            }

            @Override
            public void visit(WithItem withItem) {

            }
        });
        return fromClause[0];
    }

    private static String getWhereClause(String sql) throws JSQLParserException {
        final String[] whereClause = new String[1];
        Select select = parserSelect(sql);
        select.getSelectBody().accept(new SelectVisitor() {
            @Override
            public void visit(PlainSelect plainSelect) {
                Expression expression = plainSelect.getWhere();
                if (expression != null) {
                    whereClause[0] = expression.toString();
                }
            }

            @Override
            public void visit(SetOperationList setOpList) {

            }

            @Override
            public void visit(WithItem withItem) {

            }
        });
        return whereClause[0];
    }

    private static String getGroupClause(String sql) throws JSQLParserException {
        final String[] groupClause = new String[1];
        Select select = parserSelect(sql);
        select.getSelectBody().accept(new SelectVisitor() {
            @Override
            public void visit(PlainSelect plainSelect) {
                List<Expression> groupByColumnReferences = plainSelect.getGroupByColumnReferences();
                if (groupByColumnReferences != null) {
                    String s = groupByColumnReferences.toString();
                    groupClause[0] = s.substring(1, s.length() - 1);
                }
            }

            @Override
            public void visit(SetOperationList setOpList) {

            }

            @Override
            public void visit(WithItem withItem) {

            }
        });
        return groupClause[0];
    }

    /**
     * SQL结构
     */
    public static class SqlStructure {
        private String type;
        private String selectClause;
        private String fromClause;
        private String whereClause;
        private String groupClause;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSelectClause() {
            return selectClause;
        }

        public void setSelectClause(String selectClause) {
            this.selectClause = selectClause;
        }

        public String getFromClause() {
            return fromClause;
        }

        public void setFromClause(String fromClause) {
            this.fromClause = fromClause;
        }

        public String getWhereClause() {
            return whereClause;
        }

        public void setWhereClause(String whereClause) {
            this.whereClause = whereClause;
        }

        public String getGroupClause() {
            return groupClause;
        }

        public void setGroupClause(String groupClause) {
            this.groupClause = groupClause;
        }
    }
}
