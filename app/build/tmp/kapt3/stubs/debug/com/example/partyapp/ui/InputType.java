package com.example.partyapp.ui;

import android.util.Log;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import com.example.partyapp.R;
import com.example.partyapp.ui.theme.*;
import com.example.partyapp.viewModel.UserViewModel;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0013\u0014B\'\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\u0002\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/example/partyapp/ui/InputType;", "", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/ui/text/input/VisualTransformation;)V", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "getLabel", "()Ljava/lang/String;", "getVisualTransformation", "()Landroidx/compose/ui/text/input/VisualTransformation;", "Name", "Password", "Lcom/example/partyapp/ui/InputType$Name;", "Lcom/example/partyapp/ui/InputType$Password;", "app_debug"})
public abstract class InputType {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.ui.graphics.vector.ImageVector icon = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.foundation.text.KeyboardOptions keyboardOptions = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.ui.text.input.VisualTransformation visualTransformation = null;
    
    private InputType(java.lang.String label, androidx.compose.ui.graphics.vector.ImageVector icon, androidx.compose.foundation.text.KeyboardOptions keyboardOptions, androidx.compose.ui.text.input.VisualTransformation visualTransformation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.ui.graphics.vector.ImageVector getIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.foundation.text.KeyboardOptions getKeyboardOptions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.ui.text.input.VisualTransformation getVisualTransformation() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/ui/InputType$Name;", "Lcom/example/partyapp/ui/InputType;", "()V", "app_debug"})
    public static final class Name extends com.example.partyapp.ui.InputType {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.ui.InputType.Name INSTANCE = null;
        
        private Name() {
            super(null, null, null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/partyapp/ui/InputType$Password;", "Lcom/example/partyapp/ui/InputType;", "()V", "app_debug"})
    public static final class Password extends com.example.partyapp.ui.InputType {
        @org.jetbrains.annotations.NotNull
        public static final com.example.partyapp.ui.InputType.Password INSTANCE = null;
        
        private Password() {
            super(null, null, null, null);
        }
    }
}