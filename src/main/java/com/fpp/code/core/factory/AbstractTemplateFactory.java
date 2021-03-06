package com.fpp.code.core.factory;

import com.fpp.code.core.config.CodeConfigException;
import com.fpp.code.core.factory.config.MultipleTemplateDefinition;
import com.fpp.code.core.factory.config.TemplateDefinition;
import com.fpp.code.core.template.MultipleTemplate;
import com.fpp.code.core.template.Template;

import java.io.IOException;
import java.util.Objects;

/**
 * @author fpp
 */
public abstract class AbstractTemplateFactory extends DefaultTemplateRegistry implements ConfigurableTemplateFactory {
    @Override
    public void removeTemplate(String templateName) throws CodeConfigException, IOException {
        super.removeTemplate(templateName);
    }

    @Override
    public Template getTemplate(String templateName) throws CodeConfigException, IOException {
        return doGetTemplate(templateName);
    }

    @Override
    public MultipleTemplate getMultipleTemplate(String templateName) throws CodeConfigException, IOException {
        return doGetMultipleTemplate(templateName);
    }

    protected MultipleTemplate doGetMultipleTemplate(String templateName) throws CodeConfigException, IOException {
        MultipleTemplate multipleTemplate = getSingletonMultipleTemplate(templateName);
        if (null == multipleTemplate) {
            MultipleTemplateDefinition multipleTemplateDefinition = getMultipleTemplateDefinition(templateName);
            Objects.requireNonNull(multipleTemplateDefinition,"not found "+templateName+" multiple template definition");
            multipleTemplate = createMultipleTemplate(templateName, multipleTemplateDefinition);
        }
        return multipleTemplate;
    }

    protected Template doGetTemplate(String templateName) throws CodeConfigException, IOException {
        Template template = getSingletonTemplate(templateName);
        if (null == template) {
            TemplateDefinition templateDefinition = getTemplateDefinition(templateName);
            Objects.requireNonNull(templateDefinition,"not found "+templateName+" template definition");
            template = createTemplate(templateName, templateDefinition);
        }
        return template;
    }
    @Override
    public void removeMultipleTemplate(String templateName) throws CodeConfigException, IOException {
        super.removeMultipleTemplate(templateName);
    }
    /**
     * 获取组合模板定义
     *
     * @param templateName
     * @return
     */
    protected abstract MultipleTemplateDefinition getMultipleTemplateDefinition(String templateName);

    /**
     * 创建组合模板
     *
     * @param templateName
     * @param multipleTemplateDefinition
     * @return
     */
    protected abstract MultipleTemplate createMultipleTemplate(String templateName, MultipleTemplateDefinition multipleTemplateDefinition) throws CodeConfigException, IOException;

    /**
     * 获取模板定义
     *
     * @param templateName
     * @return
     */
    protected abstract TemplateDefinition getTemplateDefinition(String templateName);

    /**
     * 创建模板
     */
    protected abstract Template createTemplate(String templateName, TemplateDefinition templateDefinition) throws CodeConfigException, IOException;
}