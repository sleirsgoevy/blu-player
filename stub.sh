class_name="$1"
class_path="$(echo -n "$class_name" | tr '.' '/')"
pkg_path="$(dirname "$class_path")"
pkg_name="$(echo -n "$pkg_path" | tr '/' '.')"
class_basename="$(basename "$class_path")"

mkdir -p src/"$pkg_path"
cat > src/"$class_path".java << EOF
package $pkg_name;

public class $class_basename{}
EOF
