# Contributing guide

## Presentation

This project use `Gitflow`.

Documentation about `Gitflow`:

- [decription](https://www.atlassian.com/fr/git/tutorials/comparing-workflows/gitflow-workflow),
- [command usage](https://danielkummer.github.io/git-flow-cheatsheet/#),
- [schema](https://github.com/OutsideEIP/OutsideServer/blob/develop/documentation/assets/gitflow.jpg).



Two branches have been defined :

- `master` : default branch, production mirror,
- `develop`: integration branch, it's the preproduction, it must build at 100% without any problem.

Other types of branch are :

- `feature` (from `develop`) : feature branch
- `release` (from `develop`) : delivery branch
- `fix` (from `develop`) : correction branch
- `hotfix` (from `master`) : correction branch, have modifications on anomalies detected in prod
- `poc` (from `develop`) : branch for testing something
- `documentation` (from `develop`) : branch for adding documentation

Example :

```bash
$ git branch -b poc/jenkins
```

Example of branch attach to a DoDs :

```bash
$ git flow feature start <[DoDs number]>-<feature name>
$ git flow feature start 1.0.1-jenkins
```

https://user-images.githubusercontent.com/62943574/166144839-f615f491-b5bc-4751-83b0-cb4dc4c08ace.mp4


## Pull request

- Name : `[DoDs number] [type] : DoDs name`,
- Assignees : Select people in charge of this,
- Add description : Follow the template.
- Add labels

Not necessary:

- Add reviewer(s)

https://user-images.githubusercontent.com/62943574/166144857-8db92fc7-8f52-4679-9918-beef614f34a4.mp4

## Issues

- Name : `[DoDs number] [type] : DoDs name`,
- Assignees : Select people in charge of this,
- Add description : Follow the template.

https://user-images.githubusercontent.com/62943574/168418598-a3bea098-5eb0-46bf-9bb8-8a70379002ed.mp4

:warning: **The issue description have to contains the same as the PLD document.**

## How to write a commit :

A modification of file have to be associated to a message that respect the format below :

If you are IN a branch:

```bash
<type> : <description>

[optional body]
```

If you are NOT in a branch:

```bash
<issue(s) number> <type> : <description>

[optional body]
```

The `type` can be like :

- `build` : changes affecting the system build or its external dependencies
- `ci` : changes to the configuration files or the CI script
- `cd` : changes to the configuration files or the CD script
- `doc` : changes about documentation
- `feature` : adding functionality
- `perf` : changes made to improve the performance
- `refactor` : changes made that do not include features or bug fixes
- `style` : changes that don't change the meaning of the code (formatting, spaces, semicolon, dot, ...)
- `test` : addition of tests or correction of existing test

Examples :

```bash
#4 docs : update CONTRIBUTING.md : add example, usage
```

```bash
feature : jenkins setup
```
