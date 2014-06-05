USE [eni_ecole]
GO
/****** Object:  Table [dbo].[Formation]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Formation](
	[CodeFormation] [char](8) NOT NULL,
	[LibelleLong] [varchar](200) NOT NULL,
	[LibelleCourt] [varchar](50) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[TauxHoraire] [float] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[CodeTitre] [char](8) NULL,
	[PrixPublicEnCours] [float] NOT NULL,
	[HeuresCentre] [smallint] NULL,
	[HeuresStage] [smallint] NULL,
	[SemainesCentre] [tinyint] NULL,
	[SemainesStage] [tinyint] NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_Formation] PRIMARY KEY CLUSTERED 
(
	[CodeFormation] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fonction]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fonction](
	[CodeFonction] [char](5) NOT NULL,
	[Libelle] [varchar](100) NULL,
 CONSTRAINT [PK_Fonction] PRIMARY KEY CLUSTERED 
(
	[CodeFonction] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Entreprise]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Entreprise](
	[CodeEntreprise] [int] NOT NULL,
	[RaisonSociale] [varchar](255) NOT NULL,
	[Adresse1] [varchar](500) NULL,
	[Adresse2] [varchar](500) NULL,
	[Adresse3] [varchar](500) NULL,
	[CodePostal] [char](5) NULL,
	[Ville] [varchar](100) NULL,
	[Telephone] [char](14) NULL,
	[Fax] [char](14) NULL,
	[SiteWeb] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Observation] [varchar](max) NULL,
	[CodeTypeEntreprise] [char](5) NOT NULL,
	[CodeRegion] [char](2) NOT NULL,
	[CodeSecteur] [int] NOT NULL,
	[CodeOrganisme] [int] NULL,
 CONSTRAINT [PK_Entreprise] PRIMARY KEY CLUSTERED 
(
	[CodeEntreprise] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DROIT]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DROIT](
	[DRT_ID] [int] NOT NULL,
	[DRT_CODE] [varchar](10) NOT NULL,
	[DRT_LIBELLE] [varchar](50) NOT NULL,
 CONSTRAINT [PK_DROIT] PRIMARY KEY NONCLUSTERED 
(
	[DRT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PROFIL]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PROFIL](
	[PRF_ID] [int] NOT NULL,
	[PRF_CODE] [varchar](10) NOT NULL,
	[PRF_LIBELLE] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PROFIL] PRIMARY KEY NONCLUSTERED 
(
	[PRF_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PROFESSIONNEL_HOMOLOGUE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PROFESSIONNEL_HOMOLOGUE](
	[PRF_HMG_ID] [int] NOT NULL,
	[PRF_HMG_CIVILITE] [char](3) NULL,
	[PRF_HMG_NOM] [varchar](50) NULL,
	[PRF_HMG_PRENOM] [varchar](50) NULL,
	[PRF_HMG_ADRESSE1] [varchar](500) NULL,
	[PRF_HMG_ADRESSE2] [varchar](500) NULL,
	[PRF_HMG_ADRESSE3] [varchar](500) NULL,
	[PRF_HMG_CODE_POSTAL] [char](5) NULL,
	[PRF_HMG_VILLE] [varchar](100) NULL,
	[PRF_HMG_TELEPHONE_FIXE] [char](14) NULL,
	[PRF_HMG_TELEPHONE_PORTABLE] [char](14) NULL,
	[PRF_HMG_EMAIL] [varchar](100) NULL,
	[PRF_HMG_DATE_NAISSANCE] [datetime] NULL,
	[PRF_HMG_CODE_REGION_NAISSANCE] [char](2) NULL,
	[PRF_HMG_NATIONALITE] [char](2) NULL,
	[PRF_HMG_PERMIS] [bit] NULL,
	[PRF_HMG_PHOTO] [varchar](100) NULL,
 CONSTRAINT [PK_PROFESSIONNEL_HOMOLOGUE] PRIMARY KEY NONCLUSTERED 
(
	[PRF_HMG_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Module]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Module](
	[Libelle] [varchar](200) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[PrixPublicEnCours] [float] NOT NULL,
	[LibelleCourt] [varchar](20) NOT NULL,
	[IdModule] [int] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[IdModule] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PARAMETRE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PARAMETRE](
	[CONSTANT] [varchar](30) NOT NULL,
	[VALEUR] [varchar](250) NULL,
 CONSTRAINT [PK_PARAMETRE] PRIMARY KEY NONCLUSTERED 
(
	[CONSTANT] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UniteFormation]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UniteFormation](
	[Libelle] [varchar](200) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[LibelleCourt] [varchar](10) NOT NULL,
	[IdUniteFormation] [int] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_UniteFormation] PRIMARY KEY CLUSTERED 
(
	[IdUniteFormation] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TITRE_PROFESSIONNEL]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TITRE_PROFESSIONNEL](
	[TR_PRF_ID] [int] NOT NULL,
	[TR_PRF_CODE] [varchar](10) NOT NULL,
	[TR_PRF_LIEN_DOC_REFERENCES] [varchar](100) NULL,
 CONSTRAINT [PK_TITRE_PROFESSIONNEL] PRIMARY KEY NONCLUSTERED 
(
	[TR_PRF_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VERSION_ECF]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VERSION_ECF](
	[VERS_ECF_ID] [int] NOT NULL,
	[VERS_ECF_MODULE] [varchar](250) NOT NULL,
	[VERS_ECF_LIEN_SUJET] [varchar](250) NOT NULL,
	[VERS_ECF_LIEN_MODELE_CORRECTION] [varchar](250) NOT NULL,
	[VERS_ECF_LIEN_GRILLE_CORRECTION] [varchar](250) NOT NULL,
 CONSTRAINT [PK_VERSION_ECF] PRIMARY KEY NONCLUSTERED 
(
	[VERS_ECF_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Stagiaire](
	[CodeStagiaire] [int] NOT NULL,
	[Civilite] [char](3) NULL,
	[Nom] [nvarchar](50) NULL,
	[Prenom] [nvarchar](50) NULL,
	[Adresse1] [varchar](500) NOT NULL,
	[Adresse2] [varchar](500) NULL,
	[Adresse3] [varchar](500) NULL,
	[Codepostal] [char](5) NULL,
	[Ville] [varchar](100) NULL,
	[TelephoneFixe] [char](14) NULL,
	[TelephonePortable] [char](14) NULL,
	[Email] [varchar](100) NULL,
	[DateNaissance] [smalldatetime] NULL,
	[CodeRegion] [char](2) NULL,
	[CodeNationalite] [char](2) NULL,
	[CodeOrigineMedia] [char](2) NULL,
	[DateDernierEnvoiDoc] [datetime] NULL,
	[DateCreation] [datetime] NULL,
	[Repertoire] [varchar](100) NULL,
	[Permis] [bit] NOT NULL,
	[Photo] [varchar](100) NULL,
	[EnvoiDocEnCours] [bit] NOT NULL,
	[Historique] [varchar](max) NULL,
 CONSTRAINT [PK_Stagiaire] PRIMARY KEY CLUSTERED 
(
	[CodeStagiaire] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Salle]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Salle](
	[CodeSalle] [varchar](5) NOT NULL,
	[Libelle] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Salle] PRIMARY KEY CLUSTERED 
(
	[CodeSalle] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RESERVATION_SALLE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RESERVATION_SALLE](
	[RES_SALLE_ID] [int] NOT NULL,
	[RES_SALLE_DATE_DEBUT] [datetime] NOT NULL,
	[RES_SALLE_DATE_FIN] [datetime] NOT NULL,
	[RES_SALLE_NB_POSTE_LIBRE] [int] NULL,
	[RES_SALLE_SALLE] [varchar](5) NOT NULL,
 CONSTRAINT [PK_SALLE_RESERVEE] PRIMARY KEY NONCLUSTERED 
(
	[RES_SALLE_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Promotion](
	[CodePromotion] [char](8) NOT NULL,
	[Libelle] [varchar](200) NOT NULL,
	[Debut] [datetime] NOT NULL,
	[Fin] [datetime] NOT NULL,
	[CodeFormation] [char](8) NOT NULL,
	[PrixPublicAffecte] [float] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[PrixPECAffecte] [float] NOT NULL,
	[PrixFinanceAffecte] [float] NOT NULL,
 CONSTRAINT [PK_Promotion] PRIMARY KEY CLUSTERED 
(
	[CodePromotion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UTILISATEUR]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UTILISATEUR](
	[UTL_ID] [int] NOT NULL,
	[UTIL_FONCTION] [char](5) NULL,
	[UTIL_CIVILITE] [char](3) NOT NULL,
	[UTIL_NOM] [varchar](50) NOT NULL,
	[UTIL_PRENOM] [varchar](50) NOT NULL,
	[UTIL_TELEPHONE_FIXE] [char](14) NULL,
	[UTIL_TELEPHONE_PORTABLE] [char](14) NULL,
	[UTIL_EMAIL] [varchar](100) NOT NULL,
	[UTIL_PHOTO] [varchar](100) NULL,
	[UTIL_MOT_PASSE] [varchar](30) NOT NULL,
	[UTIL_PROFIL] [int] NOT NULL,
 CONSTRAINT [PK_UTILISATEUR] PRIMARY KEY NONCLUSTERED 
(
	[UTL_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UniteParFormation]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UniteParFormation](
	[CodeFormation] [char](8) NOT NULL,
	[Position] [tinyint] NOT NULL,
	[Id] [int] NOT NULL,
	[IdUniteFormation] [int] NOT NULL,
 CONSTRAINT [PK_UniteParFormation] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[StagiaireParEntreprise]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[StagiaireParEntreprise](
	[CodeStagiaire] [int] NOT NULL,
	[CodeEntreprise] [int] NOT NULL,
	[DateLien] [datetime] NOT NULL,
	[CodeTypeLien] [char](5) NOT NULL,
	[DateDebutEnEts] [datetime] NULL,
	[DateFinEnEts] [datetime] NULL,
	[CodeFonction] [char](5) NULL,
	[Commentaire] [nvarchar](max) NULL,
	[NumLien] [int] NOT NULL,
 CONSTRAINT [PK_StagiaireParEntreprise] PRIMARY KEY CLUSTERED 
(
	[NumLien] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HOMOLOGATION]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOMOLOGATION](
	[HMG_ID] [int] NOT NULL,
	[HMG_PROFESIONNEL_HOMOLOGUE] [int] NOT NULL,
	[HMG_TITRE_PROFESIONNEL] [int] NOT NULL,
	[HMG_DATE_DEBUT] [datetime] NOT NULL,
	[HMG_DATE_FIN] [datetime] NOT NULL,
 CONSTRAINT [PK_HOMOLOGATION] PRIMARY KEY NONCLUSTERED 
(
	[HMG_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DROIT_PROFIL]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DROIT_PROFIL](
	[PRF_ID] [int] NOT NULL,
	[DRT_ID] [int] NOT NULL,
 CONSTRAINT [PK_DROIT_PROFIL] PRIMARY KEY CLUSTERED 
(
	[PRF_ID] ASC,
	[DRT_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Contact]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Contact](
	[CodeContact] [int] NOT NULL,
	[Nom] [varchar](50) NOT NULL,
	[Prenom] [varchar](50) NULL,
	[TelFixe] [char](14) NULL,
	[TelMobile] [char](14) NULL,
	[Fax] [char](14) NULL,
	[Email] [varchar](50) NULL,
	[CodeFonction] [char](5) NOT NULL,
	[Observation] [varchar](1000) NULL,
	[CodeEntreprise] [int] NOT NULL,
	[CodeImportance] [int] NOT NULL,
	[Archive] [bit] NOT NULL,
	[Civilite] [varchar](4) NULL,
 CONSTRAINT [PK_Contact] PRIMARY KEY CLUSTERED 
(
	[CodeContact] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ECF]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ECF](
	[ECF_ID] [int] NOT NULL,
	[ECF_RESERVATION_SALLE] [int] NOT NULL,
	[ECF_SURVEILLANT] [int] NOT NULL,
	[ECF_CORRECTEUR] [int] NOT NULL,
	[ECF_VERSION_ECF] [int] NOT NULL,
	[ECF_LIEN_GRILLE_CORRECTION] [varchar](250) NOT NULL,
	[ECF_LIEN_COPIES_IMMATERIELLES] [varchar](250) NULL,
	[ECF_DATE_DEBUT_PASSAGE] [datetime] NOT NULL,
	[ECF_DATE_FIN_PASSAGE] [datetime] NOT NULL,
 CONSTRAINT [PK_ECF] PRIMARY KEY NONCLUSTERED 
(
	[ECF_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AVIS]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AVIS](
	[AVIS_ID] [int] NOT NULL,
	[AVIS_STAGIAIRE] [int] NOT NULL,
	[AVIS_INSTANCE_COURS] [int] NOT NULL,
	[AVIS_TEXTE] [text] NOT NULL,
	[AVIS_DATE_SAISIE] [datetime] NOT NULL,
	[AVIS_AUTEUR] [int] NULL,
 CONSTRAINT [PK_SUIVI_AVIS] PRIMARY KEY NONCLUSTERED 
(
	[AVIS_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ABSENCE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ABSENCE](
	[ABS_ID] [int] NOT NULL,
	[ABS_STAGIAIRE] [int] NOT NULL,
	[ABS_DATE_ARRIVEE_MATIN] [datetime] NOT NULL,
	[ABS_DATE_ARRIVEE_APRES_MIDI] [datetime] NOT NULL,
	[ABS_DATE_SAISIE] [datetime] NOT NULL,
	[ABS_AUTEUR] [int] NULL,
 CONSTRAINT [PK_SUIVI_ABSENCE] PRIMARY KEY NONCLUSTERED 
(
	[ABS_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cours]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cours](
	[Debut] [datetime] NOT NULL,
	[Fin] [datetime] NOT NULL,
	[DureeReelleEnHeures] [smallint] NOT NULL,
	[CodePromotion] [char](8) NULL,
	[IdCours] [uniqueidentifier] NOT NULL,
	[PrixPublicAffecte] [float] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[IdModule] [int] NOT NULL,
	[LibelleCours] [varchar](50) NOT NULL,
	[DureePrevueEnHeures] [smallint] NOT NULL,
	[CodeSalle] [varchar](5) NULL,
	[CodeFormateur] [int] NULL,
 CONSTRAINT [PK_Cours] PRIMARY KEY CLUSTERED 
(
	[IdCours] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ECHANGE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ECHANGE](
	[ECH_ID] [int] NOT NULL,
	[ECH_AUTEUR] [int] NOT NULL,
	[ECH_STAGIAIRE] [int] NOT NULL,
	[ECH_COMMENTAIRE] [text] NOT NULL,
	[ECH_DATE_SAISIE] [datetime] NOT NULL,
 CONSTRAINT [PK_SUIVI_ECHANGE] PRIMARY KEY NONCLUSTERED 
(
	[ECH_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[INSTANCE_COURS]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[INSTANCE_COURS](
	[INST_COURS_ID] [int] NOT NULL,
	[INST_COURS_ANIMATEUR] [int] NOT NULL,
	[INST_COURS_RESERVATION_SALLE] [int] NOT NULL,
	[INST_COURS_COURS] [uniqueidentifier] NOT NULL,
 CONSTRAINT [PK_INSTANCE_COURS] PRIMARY KEY NONCLUSTERED 
(
	[INST_COURS_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlanningIndividuelFormation]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PlanningIndividuelFormation](
	[CodePlanning] [int] NOT NULL,
	[CodeStagiaire] [int] NOT NULL,
	[DateInscription] [datetime] NULL,
	[DateCreation] [datetime] NULL,
	[CodeTypeProfil] [int] NOT NULL,
	[CodeFormation] [char](8) NULL,
	[CodePromotion] [char](8) NULL,
	[DateModif] [timestamp] NOT NULL,
	[NumLien] [int] NULL,
 CONSTRAINT [PK_PlanningIndividuelFormation] PRIMARY KEY CLUSTERED 
(
	[CodePlanning] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SESSION_VALIDATION]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SESSION_VALIDATION](
	[SES_VAL_ID] [int] NOT NULL,
	[SES_VAL_TITRE_PROFESSIONNEL] [int] NOT NULL,
	[SES_VAL_RESERVATION_SALLE] [int] NOT NULL,
	[SES_VAL_DATE] [datetime] NOT NULL,
	[SES_VAL_LIEN_DOC_COLLECTES] [text] NULL,
	[SES_VAL_LIEN_DOC_GENERES] [text] NULL,
 CONSTRAINT [PK_SESSION_VALIDATION] PRIMARY KEY NONCLUSTERED 
(
	[SES_VAL_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ModuleParUnite]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ModuleParUnite](
	[Position] [tinyint] NOT NULL,
	[Id] [int] NOT NULL,
	[IdUnite] [int] NOT NULL,
	[IdModule] [int] NOT NULL,
 CONSTRAINT [PK_ModuleParUnite] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SESSION_VALIDATION_STAGIAIRE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SESSION_VALIDATION_STAGIAIRE](
	[SES_VAL_ID] [int] NOT NULL,
	[CodeStagiaire] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SES_VAL_ID] ASC,
	[CodeStagiaire] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlanningIndividuelDetail]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlanningIndividuelDetail](
	[CodePlanning] [int] NOT NULL,
	[IdCours] [uniqueidentifier] NOT NULL,
	[PrixCoursDevis] [float] NOT NULL,
	[PrixCoursPECDevis] [float] NULL,
	[PrixCoursFinanceDevis] [float] NULL,
	[Dispense] [bit] NOT NULL,
	[Inscrit] [bit] NOT NULL,
	[DebutCours] [datetime] NULL,
	[FinCours] [datetime] NULL,
	[HeuresReellesCours] [smallint] NULL,
 CONSTRAINT [PK_PlanningIndividuelDetail] PRIMARY KEY CLUSTERED 
(
	[CodePlanning] ASC,
	[IdCours] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JURY]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JURY](
	[PRF_HMG_ID] [int] NOT NULL,
	[SES_VAL_ID] [int] NOT NULL,
 CONSTRAINT [PK_JURES] PRIMARY KEY CLUSTERED 
(
	[PRF_HMG_ID] ASC,
	[SES_VAL_ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[INSTANCE_COURS_STAGIAIRE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[INSTANCE_COURS_STAGIAIRE](
	[INST_COURS_ID] [int] NOT NULL,
	[CodeStagiaire] [int] NOT NULL,
 CONSTRAINT [PK_ELEVES] PRIMARY KEY CLUSTERED 
(
	[INST_COURS_ID] ASC,
	[CodeStagiaire] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ECF_STAGIAIRE]    Script Date: 06/05/2014 22:50:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ECF_STAGIAIRE](
	[ECF_ID] [int] NOT NULL,
	[CodeStagiaire] [int] NOT NULL,
 CONSTRAINT [PK_ECF_STAGIAIRE] PRIMARY KEY CLUSTERED 
(
	[ECF_ID] ASC,
	[CodeStagiaire] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_ABSENCE_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ABSENCE]  WITH CHECK ADD  CONSTRAINT [FK_ABSENCE_Stagiaire] FOREIGN KEY([ABS_STAGIAIRE])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[ABSENCE] CHECK CONSTRAINT [FK_ABSENCE_Stagiaire]
GO
/****** Object:  ForeignKey [FK_ABSENCE_UTILISATEUR]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ABSENCE]  WITH CHECK ADD  CONSTRAINT [FK_ABSENCE_UTILISATEUR] FOREIGN KEY([ABS_AUTEUR])
REFERENCES [dbo].[UTILISATEUR] ([UTL_ID])
GO
ALTER TABLE [dbo].[ABSENCE] CHECK CONSTRAINT [FK_ABSENCE_UTILISATEUR]
GO
/****** Object:  ForeignKey [FK_AVIS_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[AVIS]  WITH CHECK ADD  CONSTRAINT [FK_AVIS_Stagiaire] FOREIGN KEY([AVIS_STAGIAIRE])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[AVIS] CHECK CONSTRAINT [FK_AVIS_Stagiaire]
GO
/****** Object:  ForeignKey [FK_AVIS_UTILISATEUR]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[AVIS]  WITH CHECK ADD  CONSTRAINT [FK_AVIS_UTILISATEUR] FOREIGN KEY([AVIS_AUTEUR])
REFERENCES [dbo].[UTILISATEUR] ([UTL_ID])
GO
ALTER TABLE [dbo].[AVIS] CHECK CONSTRAINT [FK_AVIS_UTILISATEUR]
GO
/****** Object:  ForeignKey [FK_Contact_Entreprise]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Entreprise] FOREIGN KEY([CodeEntreprise])
REFERENCES [dbo].[Entreprise] ([CodeEntreprise])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Entreprise]
GO
/****** Object:  ForeignKey [FK_Contact_Fonction]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Fonction] FOREIGN KEY([CodeFonction])
REFERENCES [dbo].[Fonction] ([CodeFonction])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Fonction]
GO
/****** Object:  ForeignKey [FK_Cours_Module]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[Cours]  WITH CHECK ADD  CONSTRAINT [FK_Cours_Module] FOREIGN KEY([IdModule])
REFERENCES [dbo].[Module] ([IdModule])
GO
ALTER TABLE [dbo].[Cours] CHECK CONSTRAINT [FK_Cours_Module]
GO
/****** Object:  ForeignKey [FK_Cours_Promotion]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[Cours]  WITH CHECK ADD  CONSTRAINT [FK_Cours_Promotion] FOREIGN KEY([CodePromotion])
REFERENCES [dbo].[Promotion] ([CodePromotion])
GO
ALTER TABLE [dbo].[Cours] CHECK CONSTRAINT [FK_Cours_Promotion]
GO
/****** Object:  ForeignKey [FK_DROIT_PROFIL_DROIT]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[DROIT_PROFIL]  WITH CHECK ADD  CONSTRAINT [FK_DROIT_PROFIL_DROIT] FOREIGN KEY([DRT_ID])
REFERENCES [dbo].[DROIT] ([DRT_ID])
GO
ALTER TABLE [dbo].[DROIT_PROFIL] CHECK CONSTRAINT [FK_DROIT_PROFIL_DROIT]
GO
/****** Object:  ForeignKey [FK_DROIT_PROFIL_PROFIL]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[DROIT_PROFIL]  WITH CHECK ADD  CONSTRAINT [FK_DROIT_PROFIL_PROFIL] FOREIGN KEY([PRF_ID])
REFERENCES [dbo].[PROFIL] ([PRF_ID])
GO
ALTER TABLE [dbo].[DROIT_PROFIL] CHECK CONSTRAINT [FK_DROIT_PROFIL_PROFIL]
GO
/****** Object:  ForeignKey [FK_ECF_RESERVATION_SALLE]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ECF]  WITH CHECK ADD  CONSTRAINT [FK_ECF_RESERVATION_SALLE] FOREIGN KEY([ECF_RESERVATION_SALLE])
REFERENCES [dbo].[RESERVATION_SALLE] ([RES_SALLE_ID])
GO
ALTER TABLE [dbo].[ECF] CHECK CONSTRAINT [FK_ECF_RESERVATION_SALLE]
GO
/****** Object:  ForeignKey [FK_ECF_VERSION_ECF]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ECF]  WITH CHECK ADD  CONSTRAINT [FK_ECF_VERSION_ECF] FOREIGN KEY([ECF_VERSION_ECF])
REFERENCES [dbo].[VERSION_ECF] ([VERS_ECF_ID])
GO
ALTER TABLE [dbo].[ECF] CHECK CONSTRAINT [FK_ECF_VERSION_ECF]
GO
/****** Object:  ForeignKey [FK_ECF_STAGIAIRE_ECF]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ECF_STAGIAIRE]  WITH CHECK ADD  CONSTRAINT [FK_ECF_STAGIAIRE_ECF] FOREIGN KEY([ECF_ID])
REFERENCES [dbo].[ECF] ([ECF_ID])
GO
ALTER TABLE [dbo].[ECF_STAGIAIRE] CHECK CONSTRAINT [FK_ECF_STAGIAIRE_ECF]
GO
/****** Object:  ForeignKey [FK_ECHANGE_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ECHANGE]  WITH CHECK ADD  CONSTRAINT [FK_ECHANGE_Stagiaire] FOREIGN KEY([ECH_STAGIAIRE])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[ECHANGE] CHECK CONSTRAINT [FK_ECHANGE_Stagiaire]
GO
/****** Object:  ForeignKey [FK_ECHANGE_UTILISATEUR]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ECHANGE]  WITH CHECK ADD  CONSTRAINT [FK_ECHANGE_UTILISATEUR] FOREIGN KEY([ECH_AUTEUR])
REFERENCES [dbo].[UTILISATEUR] ([UTL_ID])
GO
ALTER TABLE [dbo].[ECHANGE] CHECK CONSTRAINT [FK_ECHANGE_UTILISATEUR]
GO
/****** Object:  ForeignKey [FK_HOMOLOGATION_PROFESSIONNEL_HOMOLOGUE]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[HOMOLOGATION]  WITH CHECK ADD  CONSTRAINT [FK_HOMOLOGATION_PROFESSIONNEL_HOMOLOGUE] FOREIGN KEY([HMG_PROFESIONNEL_HOMOLOGUE])
REFERENCES [dbo].[PROFESSIONNEL_HOMOLOGUE] ([PRF_HMG_ID])
GO
ALTER TABLE [dbo].[HOMOLOGATION] CHECK CONSTRAINT [FK_HOMOLOGATION_PROFESSIONNEL_HOMOLOGUE]
GO
/****** Object:  ForeignKey [FK_HOMOLOGATION_TITRE_PROFESSIONNEL]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[HOMOLOGATION]  WITH CHECK ADD  CONSTRAINT [FK_HOMOLOGATION_TITRE_PROFESSIONNEL] FOREIGN KEY([HMG_TITRE_PROFESIONNEL])
REFERENCES [dbo].[TITRE_PROFESSIONNEL] ([TR_PRF_ID])
GO
ALTER TABLE [dbo].[HOMOLOGATION] CHECK CONSTRAINT [FK_HOMOLOGATION_TITRE_PROFESSIONNEL]
GO
/****** Object:  ForeignKey [FK_INSTANCE_COURS_RESERVATION_SALLE]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[INSTANCE_COURS]  WITH CHECK ADD  CONSTRAINT [FK_INSTANCE_COURS_RESERVATION_SALLE] FOREIGN KEY([INST_COURS_RESERVATION_SALLE])
REFERENCES [dbo].[RESERVATION_SALLE] ([RES_SALLE_ID])
GO
ALTER TABLE [dbo].[INSTANCE_COURS] CHECK CONSTRAINT [FK_INSTANCE_COURS_RESERVATION_SALLE]
GO
/****** Object:  ForeignKey [FK_INSTANCE_COURS_UTILISATEUR]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[INSTANCE_COURS]  WITH CHECK ADD  CONSTRAINT [FK_INSTANCE_COURS_UTILISATEUR] FOREIGN KEY([INST_COURS_ANIMATEUR])
REFERENCES [dbo].[UTILISATEUR] ([UTL_ID])
GO
ALTER TABLE [dbo].[INSTANCE_COURS] CHECK CONSTRAINT [FK_INSTANCE_COURS_UTILISATEUR]
GO
/****** Object:  ForeignKey [FK_INSTANCE_COURS_STAGIAIRE_INSTANCE_COURS]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[INSTANCE_COURS_STAGIAIRE]  WITH CHECK ADD  CONSTRAINT [FK_INSTANCE_COURS_STAGIAIRE_INSTANCE_COURS] FOREIGN KEY([INST_COURS_ID])
REFERENCES [dbo].[INSTANCE_COURS] ([INST_COURS_ID])
GO
ALTER TABLE [dbo].[INSTANCE_COURS_STAGIAIRE] CHECK CONSTRAINT [FK_INSTANCE_COURS_STAGIAIRE_INSTANCE_COURS]
GO
/****** Object:  ForeignKey [FK_INSTANCE_COURS_STAGIAIRE_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[INSTANCE_COURS_STAGIAIRE]  WITH CHECK ADD  CONSTRAINT [FK_INSTANCE_COURS_STAGIAIRE_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[INSTANCE_COURS_STAGIAIRE] CHECK CONSTRAINT [FK_INSTANCE_COURS_STAGIAIRE_Stagiaire]
GO
/****** Object:  ForeignKey [FK_JURY_PROFESSIONNEL_HOMOLOGUE]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[JURY]  WITH CHECK ADD  CONSTRAINT [FK_JURY_PROFESSIONNEL_HOMOLOGUE] FOREIGN KEY([PRF_HMG_ID])
REFERENCES [dbo].[PROFESSIONNEL_HOMOLOGUE] ([PRF_HMG_ID])
GO
ALTER TABLE [dbo].[JURY] CHECK CONSTRAINT [FK_JURY_PROFESSIONNEL_HOMOLOGUE]
GO
/****** Object:  ForeignKey [FK_JURY_SESSION_VALIDATION]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[JURY]  WITH CHECK ADD  CONSTRAINT [FK_JURY_SESSION_VALIDATION] FOREIGN KEY([SES_VAL_ID])
REFERENCES [dbo].[SESSION_VALIDATION] ([SES_VAL_ID])
GO
ALTER TABLE [dbo].[JURY] CHECK CONSTRAINT [FK_JURY_SESSION_VALIDATION]
GO
/****** Object:  ForeignKey [FK_ModuleParUnite_Module]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ModuleParUnite]  WITH CHECK ADD  CONSTRAINT [FK_ModuleParUnite_Module] FOREIGN KEY([IdModule])
REFERENCES [dbo].[Module] ([IdModule])
GO
ALTER TABLE [dbo].[ModuleParUnite] CHECK CONSTRAINT [FK_ModuleParUnite_Module]
GO
/****** Object:  ForeignKey [FK_ModuleParUnite_UniteParFormation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[ModuleParUnite]  WITH CHECK ADD  CONSTRAINT [FK_ModuleParUnite_UniteParFormation] FOREIGN KEY([IdUnite])
REFERENCES [dbo].[UniteParFormation] ([Id])
GO
ALTER TABLE [dbo].[ModuleParUnite] CHECK CONSTRAINT [FK_ModuleParUnite_UniteParFormation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelDetail_Cours]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[PlanningIndividuelDetail]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelDetail_Cours] FOREIGN KEY([IdCours])
REFERENCES [dbo].[Cours] ([IdCours])
GO
ALTER TABLE [dbo].[PlanningIndividuelDetail] CHECK CONSTRAINT [FK_PlanningIndividuelDetail_Cours]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelDetail_PlanningIndividuelFormation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[PlanningIndividuelDetail]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelDetail_PlanningIndividuelFormation] FOREIGN KEY([CodePlanning])
REFERENCES [dbo].[PlanningIndividuelFormation] ([CodePlanning])
GO
ALTER TABLE [dbo].[PlanningIndividuelDetail] CHECK CONSTRAINT [FK_PlanningIndividuelDetail_PlanningIndividuelFormation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_Formation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_Formation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_Stagiaire]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_StagiaireParEntreprise]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_StagiaireParEntreprise] FOREIGN KEY([NumLien])
REFERENCES [dbo].[StagiaireParEntreprise] ([NumLien])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_StagiaireParEntreprise]
GO
/****** Object:  ForeignKey [FK_Promotion_Formation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[Promotion]  WITH CHECK ADD  CONSTRAINT [FK_Promotion_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[Promotion] CHECK CONSTRAINT [FK_Promotion_Formation]
GO
/****** Object:  ForeignKey [FK_RESERVATION_SALLE_Salle]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[RESERVATION_SALLE]  WITH CHECK ADD  CONSTRAINT [FK_RESERVATION_SALLE_Salle] FOREIGN KEY([RES_SALLE_SALLE])
REFERENCES [dbo].[Salle] ([CodeSalle])
GO
ALTER TABLE [dbo].[RESERVATION_SALLE] CHECK CONSTRAINT [FK_RESERVATION_SALLE_Salle]
GO
/****** Object:  ForeignKey [FK_SESSION_VALIDATION_RESERVATION_SALLE]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[SESSION_VALIDATION]  WITH CHECK ADD  CONSTRAINT [FK_SESSION_VALIDATION_RESERVATION_SALLE] FOREIGN KEY([SES_VAL_RESERVATION_SALLE])
REFERENCES [dbo].[RESERVATION_SALLE] ([RES_SALLE_ID])
GO
ALTER TABLE [dbo].[SESSION_VALIDATION] CHECK CONSTRAINT [FK_SESSION_VALIDATION_RESERVATION_SALLE]
GO
/****** Object:  ForeignKey [FK_SESSION_VALIDATION_STAGIAIRE_SESSION_VALIDATION]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[SESSION_VALIDATION_STAGIAIRE]  WITH CHECK ADD  CONSTRAINT [FK_SESSION_VALIDATION_STAGIAIRE_SESSION_VALIDATION] FOREIGN KEY([SES_VAL_ID])
REFERENCES [dbo].[SESSION_VALIDATION] ([SES_VAL_ID])
GO
ALTER TABLE [dbo].[SESSION_VALIDATION_STAGIAIRE] CHECK CONSTRAINT [FK_SESSION_VALIDATION_STAGIAIRE_SESSION_VALIDATION]
GO
/****** Object:  ForeignKey [FK_SESSION_VALIDATION_STAGIAIRE_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[SESSION_VALIDATION_STAGIAIRE]  WITH CHECK ADD  CONSTRAINT [FK_SESSION_VALIDATION_STAGIAIRE_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[SESSION_VALIDATION_STAGIAIRE] CHECK CONSTRAINT [FK_SESSION_VALIDATION_STAGIAIRE_Stagiaire]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Entreprise]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Entreprise] FOREIGN KEY([CodeEntreprise])
REFERENCES [dbo].[Entreprise] ([CodeEntreprise])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Entreprise]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Fonction]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Fonction] FOREIGN KEY([CodeFonction])
REFERENCES [dbo].[Fonction] ([CodeFonction])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Fonction]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Stagiaire]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Stagiaire]
GO
/****** Object:  ForeignKey [FK_UniteParFormation_Formation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[UniteParFormation]  WITH CHECK ADD  CONSTRAINT [FK_UniteParFormation_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[UniteParFormation] CHECK CONSTRAINT [FK_UniteParFormation_Formation]
GO
/****** Object:  ForeignKey [FK_UniteParFormation_UniteFormation]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[UniteParFormation]  WITH CHECK ADD  CONSTRAINT [FK_UniteParFormation_UniteFormation] FOREIGN KEY([IdUniteFormation])
REFERENCES [dbo].[UniteFormation] ([IdUniteFormation])
GO
ALTER TABLE [dbo].[UniteParFormation] CHECK CONSTRAINT [FK_UniteParFormation_UniteFormation]
GO
/****** Object:  ForeignKey [FK_UTILISATEUR_Fonction]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[UTILISATEUR]  WITH CHECK ADD  CONSTRAINT [FK_UTILISATEUR_Fonction] FOREIGN KEY([UTIL_FONCTION])
REFERENCES [dbo].[Fonction] ([CodeFonction])
GO
ALTER TABLE [dbo].[UTILISATEUR] CHECK CONSTRAINT [FK_UTILISATEUR_Fonction]
GO
/****** Object:  ForeignKey [FK_UTILISATEUR_PROFIL]    Script Date: 06/05/2014 22:50:25 ******/
ALTER TABLE [dbo].[UTILISATEUR]  WITH CHECK ADD  CONSTRAINT [FK_UTILISATEUR_PROFIL] FOREIGN KEY([UTIL_PROFIL])
REFERENCES [dbo].[PROFIL] ([PRF_ID])
GO
ALTER TABLE [dbo].[UTILISATEUR] CHECK CONSTRAINT [FK_UTILISATEUR_PROFIL]
GO
