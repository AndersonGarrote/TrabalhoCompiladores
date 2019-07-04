# Argumentos
param([Int32]$arg1=0)

# Compilação
Invoke-Expression "javac *.java"

# Testes
$Tn = "T1", "T2", "T3"

if ($arg1 -ne 0) {
    $Tn = $Tn[$arg1-1]
}

Foreach ($Ti in $Tn) {
    "# $Ti"
    "# Corretos"
    $Testes = Invoke-Expression "dir Testes\$Ti\Corretos\*.in | select BaseName" # Nomes dos arquivos .in corretos
    Foreach ($Teste in $Testes) {
        $Nome = $Teste.BaseName
        Invoke-Expression "java Main Testes\$Ti\Corretos\$Nome.in Testes\$Ti\Corretos\$Nome.out 2>&1 | Out-Null" # Execução do teste
        if ($lastExitCode -eq 0) {
            "  $Nome | Y"
        } else {
            "  $Nome | N"
        }
    }
    "# Incorretos"
    $Testes = Invoke-Expression "dir Testes\$Ti\Incorretos\*.in | select BaseName" # Nomes dos arquivos .in incorretos
    Foreach ($Teste in $Testes) {
        $Nome = $Teste.BaseName
        Invoke-Expression "java Main Testes\$Ti\Incorretos\$Nome.in Testes\$Ti\Incorretos\$Nome.out 2>&1 | Out-Null" # Execução do teste
        if ($lastExitCode -ne 0) {
            "  $Nome | Y"
        } else {
            "  $Nome | N"
        }
    }
}