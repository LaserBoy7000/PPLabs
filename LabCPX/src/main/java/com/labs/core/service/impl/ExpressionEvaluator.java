package com.labs.core.service.impl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mariuszgromada.math.mxparser.Expression;
import com.google.inject.Inject;
import com.labs.core.service.IConstantProvider;
import com.labs.core.service.IExpressionEvaluator;


public class ExpressionEvaluator implements IExpressionEvaluator {
    @Inject
    private IConstantProvider Constants;
    private String Status = "";

    @Override
    public Boolean calcBool(String expression) {
        double rs = calcVal(expression);
        if(Double.isNaN(rs))
            return false;
        return rs == 1.0;
    }

    @Override
    public double calcVal(String expression) {
        String rd = InsertConstants(expression);
        if(rd == null)
            return Double.NaN;
        Expression ex = new Expression(rd);
        if(!ex.checkSyntax()){
            setStatus("ERROR: Requested expression is incorrect");
            return Double.NaN;
        }
        return ex.calculate();
    }

    private String InsertConstants(String expression){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(expression);
        StringBuilder out = new StringBuilder();
        int lastidx = 0;
        while(m.find()){
            String str = m.group(0);
            double c = Constants.getConstant(str);
            if(c == Double.NaN){
                setStatus(Constants.getLastOperationStatus());
                return null;
            }
            out.append(expression, lastidx, m.start());
            out.append("" + c);
            lastidx = m.end();
        }
        if(lastidx <= expression.length())
            out.append(expression, lastidx, expression.length());
        return out.toString();
    }

    @Override
    public String getLastOperationStatus() {
        return Status;
    }

    private void setStatus(String status){
        this.Status = status;
    }
}
