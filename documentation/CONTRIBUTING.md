# Contributing guide

## Presentation

Two branches have been defined :

- `master` : default branch, production mirror
- `develop`: integration branch, mirror of modification

Other types of branch are :

- `fix` (from `develop`) : correction branch
- `feature` (from `develop`) : feature branch
- `poc` (from `develop`) : branch for testing something
- `documentation` (from `develop`) : branch for adding documentation

Example :

```bash
feature/jenkins
```

Example of branch attach to a DoDs :

```bash
feature/<DoDs number>-jenkins
```

## Pull request - Issues

- Name : `[DoDs number] DoDs name`
- Assignees : Select people in charge of this
- Add description : Don't hesitate to add more than necessary
- Add labels

Not necessary:

- Add reviewer(s)

## How to write a commit :

A modification of file have to be associated to a message that respect the format below :

```bash
<issue(s) number(s)> <type> : <description>

[optional body]
```

The `type` can be like :

- `build` : changes affecting the system build or its external dependencies
- `ci` : changes to the configuration files or the CI script
- `cd` : changes to the configuration files or the CD script
- `docs` : changes about documentation
- `feature` : adding functionality
- `perf` : changes made to improve the performance
- `refactor` : changes made that do not include features or bug fixes
- `style` : changes that don't change the meaning of the code (formatting, spaces, semicolon, dot, ...)
- `test` : addition of tests or correction of existing test

Example :

```bash
#1 #2 feature : jenkins cd pipeline job
```