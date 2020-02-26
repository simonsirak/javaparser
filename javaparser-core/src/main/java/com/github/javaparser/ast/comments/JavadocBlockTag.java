package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.observer.ObservableProperty;

/**
 * A JavaDoc block tag.
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocBlockTag extends Node { 

    private JavadocDescription description;
    private BlockTagType type;

    public enum BlockTagType {
        AUTHOR,
        DEPRECATED,
        EXCEPTION,
        PARAM,
        RETURN,
        SEE,
        SERIAL,
        SERIAL_DATA,
        SERIAL_FIELD,
        SINCE,
        THROWS,
        VERSION,
        UNKNOWN;
    }

    protected JavadocBlockTag(TokenRange tokenRange, JavadocDescription description, BlockTagType type) {
        super(tokenRange);
        setDescription(description);
        setType(type);
    }

    /**
     * Set the description of the block tag     
     * @param description describes the tag
     * @return new version of current object
     */
    public JavadocBlockTag setDescription(JavadocDescription description) {         
        if (description == this.description) {            
            return this;
        }
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.description, description);
        if (this.description != null)
            this.description.setParentNode(null);
        this.description = description;
        setAsParentNodeOf(description);
        return this;
    }

    /**
     * Set the type of the block tag     
     * @param type the block tag type
     * @return new version of current object
     */
    public JavadocBlockTag setType(BlockTagType type) {         
        if(type == null) {
            this.type = BlockTagType.UNKNOWN;
        }        
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.type, type);
        this.type = type;        
        return this;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }
}
