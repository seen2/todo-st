package com.todo.todo_st.todo;
//   private String title;

//   private String description;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;

//     public Todo(String description, int id, String title) {
//         this.description = description;
//         this.id = id;
//         this.title = title;
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     @Override
//     public int hashCode() {
//         int hash = 7;
//         hash = 97 * hash + this.id;
//         hash = 97 * hash + Objects.hashCode(this.title);
//         hash = 97 * hash + Objects.hashCode(this.description);
//         return hash;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) {
//             return true;
//         }
//         if (obj == null) {
//             return false;
//         }
//         if (getClass() != obj.getClass()) {
//             return false;
//         }
//         final Todo other = (Todo) obj;
//         if (this.id != other.id) {
//             return false;
//         }
//         if (!Objects.equals(this.title, other.title)) {
//             return false;
//         }
//         return Objects.equals(this.description, other.description);
//     }

//     @Override
//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         sb.append("Todo{");
//         sb.append("id=").append(id);
//         sb.append(", title=").append(title);
//         sb.append(", description=").append(description);
//         sb.append('}');
//         return sb.toString();
//     }

// }

public record Todo(

        @Id
        Integer id,
        @NotEmpty @NonNull String title,
        @NonNull @NotEmpty String description,
        @DefaultValue("false") boolean completed,
        @Version Integer version
        
        ) {

           

    // // Overload constructor to provide a default value for 'completed'
    // public Todo(int id, @NonNull @NotEmpty String title, @NonNull @NotEmpty String description) {
    //     this(id, title, description, false); // Defaults 'completed' to false
    // }
}