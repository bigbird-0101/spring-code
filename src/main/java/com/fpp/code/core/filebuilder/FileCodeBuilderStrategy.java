package com.fpp.code.core.filebuilder;

import com.fpp.code.core.config.CoreConfig;
import com.fpp.code.core.filebuilder.definedfunction.DefinedFunctionResolver;
import com.fpp.code.core.template.Template;
import com.fpp.code.core.template.TemplateResolveException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author fpp
 * @version 1.0
 * @date 2020/6/29 11:04
 */
public interface FileCodeBuilderStrategy {

    /**
     * 设置自定义方法解析器
     * @param definedFunctionResolver
     */
    void setDefinedFunctionResolver(DefinedFunctionResolver definedFunctionResolver);

    /**
     * 文件代码生成器策略
     * @return
     */
    String doneCode() throws TemplateResolveException, IOException;

    /**
     * 文件写入的方式
     */
    void fileWrite(String code) throws IOException;
}
