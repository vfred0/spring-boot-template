#!/usr/bin/env python3
import os
import sys
from pathlib import Path


def update_file_content(file_path, replacements):
    if not Path(file_path).exists():
        print(f"Warning: {file_path} does not exist, skipping...")
        return False

    try:
        with open(file_path, 'r') as file:
            content = file.read()

        for old, new in replacements:
            content = content.replace(old, new)

        with open(file_path, 'w') as file:
            file.write(content)

        print(f"Updated: {file_path}")
        return True
    except Exception as e:
        print(f"Error updating {file_path}: {e}")
        return False


def update_java_files(src_dir, old_name, new_name):
    if not src_dir.exists():
        print(f"Warning: {src_dir} not found, skipping Java updates")
        return

    old_class_name = f"{old_name.replace('-', ' ').title().replace(' ', '')}Application"
    new_class_name = f"{new_name.replace('-', ' ').title().replace(' ', '')}Application"

    for java_file in src_dir.glob("**/*.java"):
        with open(java_file, 'r') as f:
            content = f.read()
            content = content.replace(old_class_name, new_class_name)

        with open(java_file, 'w') as f:
            f.write(content)

        if java_file.name == f"{old_class_name}.java":
            new_file_path = java_file.parent / f"{new_class_name}.java"
            java_file.rename(new_file_path)
            print(f"Renamed: {java_file} → {new_file_path}")


def main():
    if len(sys.argv) != 2:
        print("Usage: python script.py <new-project-name>")
        sys.exit(1)

    new_project_name = sys.argv[1]
    old_project_name = "template"
    base_dir = Path(os.getcwd())

    print(f"Setting up project: {new_project_name}")

    files_to_update = {
        "settings.gradle": [
            (f"rootProject.name = '{old_project_name}'", f"rootProject.name = '{new_project_name}'")
        ],
        "docker/docker-compose.yml": [
            (f"{old_project_name}-backend", f"{new_project_name}-backend"),
            (f"{old_project_name}/backend", f"{new_project_name}/backend"),
            (f"{old_project_name}-database", f"{new_project_name}-database"),
            (f"{old_project_name}-data", f"{new_project_name}-data")
        ],
        "docker/database.yml": [
            (f"{old_project_name}-database", f"{new_project_name}-database"),
            (f"pg_isready -U {old_project_name}", f"pg_isready -U {new_project_name}"),
            (f"{old_project_name}-data", f"{new_project_name}-data")
        ],
        "docker/Dockerfile-backend": [
            (f"{old_project_name}", f"{new_project_name}")
        ]
    }

    env_file = base_dir / "docker" / ".env"
    if env_file.exists():
        files_to_update["docker/.env"] = [
            (f"DATABASE_NAME={old_project_name}", f"DATABASE_NAME={new_project_name}"),
            (f"DATABASE_USERNAME={old_project_name}", f"DATABASE_USERNAME={new_project_name}"),
            (f"DATABASE_PASSWORD={old_project_name}", f"DATABASE_PASSWORD={new_project_name}")
        ]

    for rel_path, replacements in files_to_update.items():
        file_path = base_dir / rel_path
        update_file_content(file_path, replacements)

    java_src_dir = base_dir / "src" / "main" / "java" / "com" / "gnoboa"
    update_java_files(java_src_dir, old_project_name, new_project_name)

    print(f"\nSetup complete for {new_project_name}!")

if __name__ == "__main__":
    main()